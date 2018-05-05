package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.CnCompany;
import java.util.List;

public interface CnCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CnCompany record);

    CnCompany selectByPrimaryKey(Integer id);

    List<CnCompany> selectAll();

    int updateByPrimaryKey(CnCompany record);
}