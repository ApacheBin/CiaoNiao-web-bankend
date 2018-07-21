package com.cainiaoshixi.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    private static  final String USERNAME = "Yzm@cainiaoshixi.com";

    private static  final String PASSWORD = "Yzm12345";

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.cainiaoshixi.com");   //使用自己的域名
        mailSender.setPort(465);

        mailSender.setUsername(USERNAME);
        mailSender.setPassword(PASSWORD);
        mailSender.setDefaultEncoding("UTF-8");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        return mailSender;
    }

    /**
     * 验证码邮箱模板
     */
    @Bean("VerificationCodeTemplate")
    public SimpleMailMessage getVerificationCodeMailTemplate() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(USERNAME);
        message.setSubject("验证邮箱");
        message.setText("【菜鸟实习】您的验证码是:%s");
        return message;
    }


}
