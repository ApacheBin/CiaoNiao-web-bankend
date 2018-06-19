package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.StudentCertify;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentCertifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentCertify record);

    StudentCertify selectByPrimaryKey(Integer id);

    List<StudentCertify> selectAll(@Param("start") Integer start, @Param("limit")Integer limit);

    int updateByPrimaryKey(StudentCertify record);

    StudentCertify selectByUid(Integer userId);

    int updateByUid(StudentCertify student);
}