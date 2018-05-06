package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.Company;

public interface ICompanyService {
    void addCompany(Company education);

    void updateCompany(Company education);

    void deleteCompany(Integer id);

    Company getCompanyListByUserId(Integer userId);
}
