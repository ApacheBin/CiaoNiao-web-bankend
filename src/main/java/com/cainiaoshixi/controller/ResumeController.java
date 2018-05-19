package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.File;
import com.cainiaoshixi.entity.JobSubmit;
import com.cainiaoshixi.entity.Resume;
import com.cainiaoshixi.enums.FileTypeEnum;
import com.cainiaoshixi.service.IFileService;
import com.cainiaoshixi.service.IResumeService;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import com.cainiaoshixi.vo.JobBriefVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author: Chy
 * @Description: 简历相关
 * @Date: Created at 11:33 2018/3/12
 */
@Controller
@RequestMapping("/resume")
@CrossOrigin
@Api(value = "简历控制器", tags = {"简历接口"})
public class ResumeController {

    private final IResumeService resumeService;

    private final SessionUtil session;

    private final IFileService fileService;
    @Autowired
    public ResumeController(IResumeService resumeService, SessionUtil session, IFileService fileService) {
        this.resumeService = resumeService;
        this.session = session;
        this.fileService = fileService;
    }

    @GetMapping("/get")
    @ApiOperation("根据ID获取自我评价")
    @ResponseBody
    public Result getEvaluationByUserId(){
        Integer userId = session.userId();
        return ResultUtil.success(resumeService.getEvaluationByUserId(userId));
    }

    @PostMapping("/save")
    @ApiOperation("保存自我评价")
    @ResponseBody
    public Result saveEvaluation(@RequestParam("evaluation") String evaluation) {
        Resume resume=new Resume();
        resume.setEvaluation(evaluation);
        Integer userId=session.userId();
        resume.setUserId(userId);
        int exist=resumeService.isExistEvaluation(userId);
        if(exist>0)
            resumeService.updateEvaluation(resume);
        else if(exist==0) {
            resumeService.addEvaluation(resume);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("resumeId", resume.getId());
            return ResultUtil.success(jsonObject);
        }
        return ResultUtil.success("");
    }
    @PostMapping("/submit")
    @ApiOperation("投递简历")
    @ResponseBody
    public Result submitJob(@RequestBody JobSubmit jobSubmit) {
        jobSubmit.setUserId(session.userId());
        resumeService.submitJob(jobSubmit); //这个返回的是新增的记录数
        // 返回投递ID
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("submitId", jobSubmit.getId());
        return ResultUtil.success(jsonObject);
    }

    @GetMapping("/job/list")
    @ApiOperation("获取工作岗位列表")
    @ResponseBody
    public Result getSubmitListByUId(){
        Integer userId = session.userId();
        List<JobBriefVo> jobBriefVos = resumeService.querySubmitByUserId(userId);  //条件查询
        return ResultUtil.success(jobBriefVos);
    }

    @PostMapping("/upload")
    @ApiOperation("简历上传")
    @ResponseBody
    public Result upload(@RequestParam("file")MultipartFile file) throws IOException {
        File fileEntity = new File();
        fileEntity.setUploaderId(session.userId());
        fileEntity.setType(FileTypeEnum.RESUMES.getCode());
        fileService.save(fileEntity, file);
        return ResultUtil.success("");
    }

    @GetMapping("/download")
    @ApiOperation("简历下载")
    public ResponseEntity<byte[]> getResumeFile(@RequestParam(value = "userId", required = false) Integer userId) throws IOException {
        File file;
        if(userId == null)
            file = fileService.getFile(FileTypeEnum.RESUMES.getCode(), session.userId());
        else
            file = fileService.getFile(FileTypeEnum.RESUMES.getCode(), userId);
        InputStream in = new FileInputStream(FileTypeEnum.ROOT.getPath() +
                FileTypeEnum.RESUMES.getPath() + file.getPath());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", file.getMime());
        headers.set("Content-Disposition", "attachment;filename*=UTF-8''" +
                URLEncoder.encode(file.getName(), "UTF-8")); //解决中文乱码或不出现中文名的问题
        return new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
    }

    @GetMapping("/file/get")
    @ApiOperation("获取简历文件信息")
    @ResponseBody
    public Result getResumeFileInfo(@RequestParam(value = "userId", required = false) Integer userId) {
        File file;
        if(userId == null)
            file = fileService.getFile(FileTypeEnum.RESUMES.getCode(), session.userId());
        else
            file = fileService.getFile(FileTypeEnum.RESUMES.getCode(), userId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fileId", file.getId());
        jsonObject.put("filename", file.getName());
        return ResultUtil.success(jsonObject);
    }
}