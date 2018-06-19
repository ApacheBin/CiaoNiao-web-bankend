package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.EmployeeCertify;
import com.cainiaoshixi.entity.StudentCertify;

import java.util.List;

public interface IIdentityService {
    void insert(StudentCertify student);

    void insert(EmployeeCertify employee);

    StudentCertify getStudentCertificationByUid(Integer userId);

    EmployeeCertify getEmployeeCertificationByUid(Integer userId);

    List<StudentCertify> listStuCert(Integer page);

    List<EmployeeCertify> listEmployeeCert(Integer page);

    Boolean existEmployeeCert(Integer userId);

    Boolean existStuCert(Integer userId);

    void updateByUid(StudentCertify student);

    void updateByUid(EmployeeCertify employee);
}
