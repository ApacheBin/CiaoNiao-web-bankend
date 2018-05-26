package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.StudentCertify;
import java.util.List;

public interface StudentCertifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentCertify record);

    StudentCertify selectByPrimaryKey(Integer id);

    List<StudentCertify> selectAll();

    int updateByPrimaryKey(StudentCertify record);
}