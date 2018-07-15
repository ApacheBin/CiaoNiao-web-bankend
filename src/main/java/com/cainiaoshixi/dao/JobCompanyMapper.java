package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.JobCompany;
import java.util.List;

public interface JobCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobCompany record);

    JobCompany selectByPrimaryKey(Integer id);

    List<JobCompany> selectAll();

    int updateByPrimaryKey(JobCompany record);

    int updateByPrimaryKeySelective(JobCompany record);

    List<JobCompany> getJobCompanyListByUserId(Integer id);
}