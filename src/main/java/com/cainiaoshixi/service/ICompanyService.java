package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.Company;
import com.cainiaoshixi.entity.JobCompany;

import java.util.List;

public interface ICompanyService {
    int addCompany(Company company);

    int addJobCompany(JobCompany jobCompany);

    void updateCompany(Company company);

    void updateJobCompany(JobCompany company);

    void deleteCompany(Integer id);

    void deleteJobCompany(Integer id);

    List<Company> getCompanyListByUserId(Integer userId);

    List<JobCompany> getJobCompanyListByUserId(Integer userId);
}
