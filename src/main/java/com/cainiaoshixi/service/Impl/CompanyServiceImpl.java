package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.CompanyMapper;
import com.cainiaoshixi.dao.JobCompanyMapper;
import com.cainiaoshixi.entity.Company;
import com.cainiaoshixi.entity.JobCompany;
import com.cainiaoshixi.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private JobCompanyMapper jobCompanyMapper;

    @Override
    public List<Company> getCompanyListByUserId(Integer userId){
        return companyMapper.getCompanyListByUserId(userId);
    }

    @Override
    public List<JobCompany> getJobCompanyListByUserId(Integer userId){
        return jobCompanyMapper.getJobCompanyListByUserId(userId);
    }


    @Override
    public int addCompany(Company company){
        company.setCreateTime(new Date());
        company.setUpdateTime(new Date());
        return companyMapper.insertSelective(company);
    }

    @Override
    public int addJobCompany(JobCompany jobCompany){
        jobCompany.setCreateTime(new Date());
        jobCompany.setUpdateTime(new Date());
        return jobCompanyMapper.insertSelective(jobCompany);
    }

    @Override
    public void updateCompany(Company company){
        company.setUpdateTime(new Date());
        companyMapper.updateByPrimaryKeySelective(company);
    }

    @Override
    public void updateJobCompany(JobCompany jobCompany){
        jobCompany.setUpdateTime(new Date());
        jobCompanyMapper.updateByPrimaryKeySelective(jobCompany);
    }

    @Override
    public void deleteCompany(Integer id){
        companyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteJobCompany(Integer id){
        jobCompanyMapper.deleteByPrimaryKey(id);
    }
}
