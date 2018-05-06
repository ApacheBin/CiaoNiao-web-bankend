package com.cainiaoshixi.controller;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/upload")
    @ResponseBody
    public void uploadFile(@RequestParam(value = "name", required = false) String name,
                           @RequestParam("file") MultipartFile file) {
        String fileName = file.getName();
//        if(!file.isEmpty()) {
//            try {
//                file.transferTo(new File("/tmp"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        logger.debug(fileName);
    }

    @GetMapping(value = "/download/{imageName:.+}", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public byte[] download(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        File file = new File("E:/cainiaoshixi/images/" + imageName);
        InputStream in = new FileInputStream(file);
        return IOUtils.toByteArray(in);
    }

}
