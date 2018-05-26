package com.cainiaoshixi.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface IMailService {

    void sendVerificationCode(String email) throws UnsupportedEncodingException, MessagingException;

    void sendSimpleMessage(String to, String subject, String text, String from);

    void sendMineMessage(String to, String subject, String text,
                         String from, String fromName) throws MessagingException, UnsupportedEncodingException;

}
