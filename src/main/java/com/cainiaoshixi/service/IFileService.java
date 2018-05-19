package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface IFileService {

    void save(File file, MultipartFile multipartFile) throws IOException;

    File getFile(int type, int uploaderId);
}
