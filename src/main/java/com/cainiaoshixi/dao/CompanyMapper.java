package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.Company;
import java.util.List;

public interface CompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company job);

    Company selectByPrimaryKey(Integer id);

    List<Company> selectAll();

    int updateByPrimaryKey(Company record);

    int updateByPrimaryKeySelective(Company record);

    List<Company> getCompanyListByUserId(Integer id);
}