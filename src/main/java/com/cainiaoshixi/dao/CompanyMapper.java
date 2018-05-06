package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.Company;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    Company selectByPrimaryKey(Integer id);

    List<Company> selectAll();

    int updateByPrimaryKey(Company record);

    List<Company> getCompanyListById(@Param("userId") Integer userId, @Param("id") Integer id);
}