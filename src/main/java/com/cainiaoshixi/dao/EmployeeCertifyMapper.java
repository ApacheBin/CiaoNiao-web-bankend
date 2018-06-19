package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.EmployeeCertify;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeCertifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeCertify record);

    EmployeeCertify selectByPrimaryKey(Integer id);

    List<EmployeeCertify> selectAll(@Param("start") Integer start, @Param("limit")Integer limit);

    int updateByPrimaryKey(EmployeeCertify record);

    EmployeeCertify selectByUid(Integer userId);

    int updateByUid(EmployeeCertify employee);
}