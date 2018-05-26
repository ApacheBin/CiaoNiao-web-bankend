package com.cainiaoshixi.controller;

import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.EmployeeCertify;
import com.cainiaoshixi.entity.File;
import com.cainiaoshixi.entity.StudentCertify;
import com.cainiaoshixi.enums.FileTypeEnum;
import com.cainiaoshixi.service.IFileService;
import com.cainiaoshixi.service.IIdentityService;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/identity")
@CrossOrigin
public class IdentityController {

    @Autowired
    private SessionUtil session;

    @Autowired
    private IFileService fileService;

    @Autowired
    private IIdentityService identityService;

    @PostMapping("/student/upload")
    @ApiOperation("上传学生信息")
    @ResponseBody
    public Result getStudentCertification(
            @RequestParam("cert")MultipartFile cert,
            @RequestParam("realName") String realName,
            @RequestParam("school") String school,
            @RequestParam("email") String email,
            @RequestParam("code") String code) throws IOException {
        if(verifyCode(email, code)) {
            File fileEntity = new File();
            fileEntity.setUploaderId(session.userId());
            fileEntity.setType(FileTypeEnum.STUDENT.getCode());
            //保存学生证
            fileService.save(fileEntity, cert);
            StudentCertify student = new StudentCertify();
            student.setRealName(realName);
            student.setSchool(school);
            student.setEmail(email);
            student.setUserId(session.userId());
            identityService.insert(student);
            return ResultUtil.success("");
        }else {
            return ResultUtil.error(150, "验证码不正确！");
        }
    }

    @PostMapping("/employee/upload")
    @ApiOperation("上传职工信息")
    @ResponseBody
    public Result getEmployeeCertification(
            @RequestBody EmployeeCertify employee,
            @RequestParam("code") String code){
        if(verifyCode(employee.getEmail(), code)) {
            employee.setUserId(session.userId());
            identityService.insert(employee);
            return ResultUtil.success("");
        }else {
            return ResultUtil.error(150, "验证码不正确！");
        }
    }

    private boolean verifyCode(String email, String code) {
        //todo 验证码校验
        return true;
    }

}
