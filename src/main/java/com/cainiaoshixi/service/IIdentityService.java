package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.EmployeeCertify;
import com.cainiaoshixi.entity.StudentCertify;

public interface IIdentityService {
    void insert(StudentCertify student);

    void insert(EmployeeCertify employee);
}
