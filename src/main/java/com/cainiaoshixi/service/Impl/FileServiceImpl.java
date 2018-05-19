package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.FileMapper;
import com.cainiaoshixi.entity.File;
import com.cainiaoshixi.enums.FileResultEnum;
import com.cainiaoshixi.enums.FileTypeEnum;
import com.cainiaoshixi.exception.FileException;
import com.cainiaoshixi.service.IFileService;
import com.cainiaoshixi.util.FileUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class FileServiceImpl implements IFileService {

    private final static String parentDir = "/data/files/";

    private final static String[] dirList = {"/", "resumes/", "student/", "company/"};

    private final FileMapper fileMapper;

    @Autowired
    public FileServiceImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public void save(File file, MultipartFile multipartFile) throws IOException {
        //文件空，抛出异常
        if(multipartFile.isEmpty()) {
            throw new FileException(FileResultEnum.FILE_IS_EMPTY);
        }
        //根据MD5判断文件是否存在，已存在抛出异常
        String md5 = new Md5Hash(multipartFile.getBytes()).toHex();
        File savedFile = fileMapper.selectByUploaderId(file.getType(), file.getUploaderId());
        if(savedFile != null && md5.equals(savedFile.getMd5())) {
            throw new FileException(FileResultEnum.FILE_REPEAT);
        }
        file.setMd5(md5);
        String path = FileUtil.getRelativePath(multipartFile);
        file.setPath(path);
        String filename = multipartFile.getOriginalFilename();
        file.setFormat(FileUtil.getExtension(filename));
        file.setName(filename);
        file.setMime(multipartFile.getContentType());
        file.setSize(multipartFile.getSize());
        Date now = new Date();
        file.setUpdateTime(now);
        FileUtil.save(multipartFile, parentDir + dirList[file.getType()] + path);
        if (savedFile == null) {
            file.setCreateTime(now);
            fileMapper.insert(file);
        }else {
            FileUtil.delete(parentDir + dirList[file.getType()] + savedFile.getPath());
            file.setId(savedFile.getId());
            fileMapper.updateByPrimaryKey(file);
        }
    }

    public File getFile(int type, int uploaderId) {
        return fileMapper.selectByUploaderId(type, uploaderId);
    }

    @Override
    public File getFile(Integer id) {
        return fileMapper.selectByPrimaryKey(id);
    }
}
