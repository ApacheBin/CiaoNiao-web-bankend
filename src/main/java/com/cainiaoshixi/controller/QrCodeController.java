package com.cainiaoshixi.controller;

import com.cainiaoshixi.util.RandomUtil;
import com.google.zxing.WriterException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/qrcode")
@CrossOrigin
public class QrCodeController {

    @GetMapping("/get")
    public void getQRCode(HttpServletResponse response) throws WriterException, IOException {
        String uuid = UUID.randomUUID().toString().replaceAll("-", ""); //生成uuid
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.addHeader("token", uuid);
        RandomUtil.generateQRcode(uuid, response.getOutputStream());
    }
}