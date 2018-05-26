package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.service.IMailService;
import com.cainiaoshixi.util.RandomUtil;
import com.cainiaoshixi.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@Async
public class MailServiceImpl implements IMailService {

    @Autowired
    public JavaMailSender mailSender;

    @Autowired
    public SimpleMailMessage verificationCodeTemplate;

    @Autowired
    public RedisUtil redisUtil;

    public static final String VERIFICATION_CODE_KEY = "verification_code";


    public void sendSimpleMessage(String to, String subject, String text, String from) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(from);
        mailSender.send(message);
    }

    public void sendMineMessage(String to, String subject, String text,
                                String from, String fromName) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        helper.setFrom(from, fromName);
        mailSender.send(message);
    }

    public void sendVerificationCode(String to) throws UnsupportedEncodingException, MessagingException {
        String code = RandomUtil.generateVerificationCode();
        redisUtil.hset(VERIFICATION_CODE_KEY, to, code, 600);
        String text = String.format(verificationCodeTemplate.getText(), code);
        sendMineMessage(to, verificationCodeTemplate.getSubject(), text,
                verificationCodeTemplate.getFrom(), "菜鸟实习邮箱验证码");
    }
}
