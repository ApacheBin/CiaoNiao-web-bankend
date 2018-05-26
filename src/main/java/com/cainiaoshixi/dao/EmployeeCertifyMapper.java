package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.EmployeeCertify;
import java.util.List;

public interface EmployeeCertifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeCertify record);

    EmployeeCertify selectByPrimaryKey(Integer id);

    List<EmployeeCertify> selectAll();

    int updateByPrimaryKey(EmployeeCertify record);
}