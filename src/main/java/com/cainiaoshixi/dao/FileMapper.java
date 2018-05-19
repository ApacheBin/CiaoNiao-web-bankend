package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.File;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(File record);

    File selectByPrimaryKey(Integer id);

    List<File> selectAll();

    int updateByPrimaryKey(File record);

    int selectByMd5(@Param("md5") String md5, @Param("uploaderId") Integer uploaderId);

    File selectByUploaderId(@Param("type") Integer type, @Param("uploaderId") Integer uploaderId);
}