package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.CompanyMapper;
import com.cainiaoshixi.entity.Company;
import com.cainiaoshixi.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Company getCompanyListByUserId(Integer userId){
        Integer companyId = 1;
        return companyMapper.selectByPrimaryKey(companyId);
    }

    @Override
    public void addCompany(Company company){
        company.setCreateTime(new Date());
        company.setUpdateTime(new Date());
        companyMapper.insert(company);
    }

    @Override
    public void updateCompany(Company company){
        company.setUpdateTime(new Date());
        companyMapper.updateByPrimaryKeySelective(company);
    }

    @Override
    public void deleteCompany(Integer id){
        companyMapper.deleteByPrimaryKey(id);
    }
}
