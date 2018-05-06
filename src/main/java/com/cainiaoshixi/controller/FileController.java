package com.cainiaoshixi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/upload")
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

}
