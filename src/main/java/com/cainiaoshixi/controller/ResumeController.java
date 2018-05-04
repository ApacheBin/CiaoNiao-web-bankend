package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.*;
import com.cainiaoshixi.service.IResumeService;
import com.cainiaoshixi.util.RedisUtil;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
@ResponseBody
public class ResumeController {
    @Autowired
    public final IResumeService resumeService;
    private RedisUtil redisUtil = new RedisUtil();
    private final SessionUtil session;
    @Autowired
    public ResumeController(IResumeService resumeService, SessionUtil session) {
        this.resumeService = resumeService;
        this.session = session;
    }
    /**
     * @Author: Chy
     * @Param:
     * @Description: 编辑简历
     * @Date: 11:36 2018/3/12
     */
    @RequestMapping("/editResume")
    public String editResume(Model model, @RequestParam String userId) {

        WxUser wxUser = resumeService.getUserByUserId(userId);
        List<Education> educationList = resumeService.getEducationListByUserId(userId);
        List<WorkExperience> workExperienceList = resumeService.getWorkExpListByUserId(userId);

        model.addAttribute("wxUser", wxUser);
        model.addAttribute("educationList", educationList);
        model.addAttribute("workExperienceList", workExperienceList);

        return "editResume";
    }

    /**
     * @Author: Chy
     * @Param:
     * @Description: 保存用户基本信息
     * @Date: 0:45 2018/3/13
     */
    @RequestMapping("/savePersonInfo")
    @ResponseBody
    public String savePersonInfo(@RequestBody WxUser wxUser) {
        resumeService.savePersonInfo(wxUser);
        return "success";
    }

    /**
     * @Author: Chy
     * @Param:
     * @Description: 保存用户的教育经历
     * @Date: 1:13 2018/3/13
     */
    @RequestMapping("/saveEducationExp")
    @ResponseBody
    public String saveEducationExp(@RequestBody List<Education> educationList) {
        for (Education education : educationList) {
            resumeService.saveEducationExp(education);
        }
        return "success";
    }

    @RequestMapping("/saveWorkExp")
    @ResponseBody
    public String saveWorkExp(@RequestBody List<WorkExperience> workExperienceList) {

        for (WorkExperience workExperience : workExperienceList){
            resumeService.saveWorkExp(workExperience);
        }
        return "success";
    }

    @GetMapping("/get")
    @ApiOperation("根据ID获取自我评价")
    public Result getEvaluationByUserId(){
        Integer userId = session.userId();
        return ResultUtil.success(resumeService.getEvaluationByUserId(userId));
    }

    @PostMapping("/save")
    @ApiOperation("保存自我评价")
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
}