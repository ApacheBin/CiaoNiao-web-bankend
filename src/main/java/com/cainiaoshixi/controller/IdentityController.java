package com.cainiaoshixi.controller;

import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.EmployeeCertify;
import com.cainiaoshixi.entity.File;
import com.cainiaoshixi.entity.StudentCertify;
import com.cainiaoshixi.enums.FileTypeEnum;
import com.cainiaoshixi.service.IFileService;
import com.cainiaoshixi.service.IIdentityService;
import com.cainiaoshixi.service.Impl.MailServiceImpl;
import com.cainiaoshixi.util.RedisUtil;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/student/get")
    @ResponseBody
    public Result getStudentCertification() {
        StudentCertify stuCert = identityService.getStudentCertificationByUid(session.userId());
        return ResultUtil.success(stuCert);
    }

    @GetMapping("/employee/get")
    @ResponseBody
    public Result getEmployeeCertification() {
        EmployeeCertify employeeCert = identityService.getEmployeeCertificationByUid(session.userId());
        return ResultUtil.success(employeeCert);
    }

    @GetMapping("/student/list")
    @ResponseBody
    public Result listStudentCertification(@RequestParam("page")int page) {
        return ResultUtil.success(identityService.listStuCert(page));
    }

    @GetMapping("/employee/list")
    @ResponseBody
    public Result listEmployeeCertification(@RequestParam("page")int page) {
        return ResultUtil.success(identityService.listEmployeeCert(page));
    }

    @PostMapping("/student/upload")
    @ApiOperation("上传学生信息")
    @ResponseBody
    public Result uploadStudentCertification(
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
            if(!identityService.existStuCert(session.userId()))
                identityService.insert(student);
            else
                identityService.updateByUid(student);
            return ResultUtil.success("");
        }else {
            return ResultUtil.error(150, "验证码不正确！");
        }
    }

    @PostMapping("/employee/upload")
    @ApiOperation("上传职工信息")
    @ResponseBody
    public Result uploadEmployeeCertification(
            @RequestBody @Validated EmployeeCertify employee,
            @RequestParam("code") String code){
        if(verifyCode(employee.getEmail(), code)) {
            employee.setUserId(session.userId());
            if(!identityService.existEmployeeCert(session.userId()))
                identityService.insert(employee);
            else
                identityService.updateByUid(employee);
            return ResultUtil.success("");
        }else {
            return ResultUtil.error(150, "验证码不正确！");
        }
    }

    /**
     * 校验验证码
     * @param email 接收验证码的邮箱
     * @param code 验证码
     * @return 是否验证成功
     */
    private boolean verifyCode(String email, String code) {
        return  code.equals(redisUtil.hget(MailServiceImpl.VERIFICATION_CODE_KEY, email));
    }

}
