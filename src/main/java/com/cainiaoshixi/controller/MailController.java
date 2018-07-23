package com.cainiaoshixi.controller;

import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.EmployeeCertify;
import com.cainiaoshixi.service.IMailService;
import com.cainiaoshixi.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.UnsupportedEncodingException;
import java.util.Set;

@Controller
@CrossOrigin
@RequestMapping("/mail")
public class MailController {

    private final IMailService mailService;

    private final Validator validator;

    @Autowired
    public MailController(IMailService mailService, Validator validator) {
        this.mailService = mailService;
        this.validator = validator;
    }

    @PostMapping("/verification/code/send")
    @ResponseBody
    public Result sendVerificationCode(@RequestParam("email") String email)
            throws UnsupportedEncodingException, MessagingException {
        EmployeeCertify employeeCertify = new EmployeeCertify();
        employeeCertify.setEmail(email);
        Set<ConstraintViolation<EmployeeCertify>> result =  validator.validateProperty(employeeCertify, "email");
        if(0 < result.size()) {
            return ResultUtil.error(161, "非企业邮箱或邮箱格式不对");
        }
        mailService.sendVerificationCode(email);
        return ResultUtil.success("验证码发送成功");
    }
}
