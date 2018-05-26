package com.cainiaoshixi.controller;

import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.service.IMailService;
import com.cainiaoshixi.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Controller
@CrossOrigin
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private IMailService mailService;

    @PostMapping("/verification/code/send")
    @ResponseBody
    public Result sendVerificationCode(@RequestParam("email") String email)
            throws UnsupportedEncodingException, MessagingException {
        mailService.sendVerificationCode(email);
        return ResultUtil.success("");
    }
}
