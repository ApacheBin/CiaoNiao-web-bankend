package com.cainiaoshixi.util;

import com.cainiaoshixi.enums.FileResultEnum;
import com.cainiaoshixi.exception.FileException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class FileUtil {

    /**
     * 获取相对路径
     * @param multipartFile 上传的文件或图片
     * @return 返回相对路径（默认采用MD5形成文件名）
     */
    public static String getRelativePath(MultipartFile multipartFile) throws IOException {
        return getRelativePath(multipartFile, FilenameGenerateMethod.MD5);
    }

    /**
     *
     * @param multipartFile 上传的文件或图片
     * @param method 文件名构建方法
     * @return 文件保存的相对路径
     */
    public static String getRelativePath(MultipartFile multipartFile, FilenameGenerateMethod method) throws IOException {
        String filename;
        switch(method){
            case UUID:
                //UUID作为文件名
                filename = UUID.randomUUID().toString();
                break;
            case MD5:
                //MD5作为文件名
                filename = new Md5Hash(multipartFile.getBytes()).toHex();
                break;
            default: filename = multipartFile.getOriginalFilename();
        }
        // 子目录
        String childDir = (System.currentTimeMillis() >> 22) + "/";
        // 获取原文件扩展名
        String ext = getExtension(multipartFile.getOriginalFilename());
        return childDir + filename + "." + ext;
    }

    /**
     * 将上传的文件保存到指定的路径
     * @param multipartFile 上传的文件
     * @param path 保存的路径
     */
    public static void save(MultipartFile multipartFile, String path) throws IOException {
        File file = new File(path);
        if(!file.getParentFile().exists()) {
            if(!file.getParentFile().mkdirs())
                throw new FileException(FileResultEnum.DIR_CREATE_FAILED);
        }
        multipartFile.transferTo(file);
    }

    public static boolean delete(String path) {
        return new File(path).delete();
    }

    /**
     * 获取文件的扩展名
     * @param file 文件名称
     */
    public static String getExtension(String file) {
        return file.substring(file.lastIndexOf(".") + 1);
    }

    public static String encodeByMd5(String content) {
        return new Md5Hash(content).toHex();
    }

    /**
     * 上传文件至指定路径, 生成随机文件名并返回
     * @param multipartFile
     * @param fileDir
     * @return
     */
    public static String uploadFile(MultipartFile multipartFile, String fileDir) throws IOException {
        File dir = new File(fileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String originFileName = multipartFile.getOriginalFilename();
        String suffix = originFileName.substring(originFileName.lastIndexOf(".") + 1);
        String fileName = UUID.randomUUID().toString().replace("-", "") + "." + suffix;
        String filePath = fileDir + fileName;
        multipartFile.transferTo(new File(filePath));
        return fileName;
    }

    /**
     * 文件名称构建方法
     */
    public enum FilenameGenerateMethod {
        UUID,
        MD5
    }
}
