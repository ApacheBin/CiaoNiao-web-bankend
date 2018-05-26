package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.EmployeeCertifyMapper;
import com.cainiaoshixi.dao.StudentCertifyMapper;
import com.cainiaoshixi.entity.EmployeeCertify;
import com.cainiaoshixi.entity.StudentCertify;
import com.cainiaoshixi.service.IIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IdentityServiceImpl implements IIdentityService {

    @Autowired
    private StudentCertifyMapper studentCertifyMapper;

    @Autowired
    private EmployeeCertifyMapper employeeCertifyMapper;

    @Override
    public void insert(StudentCertify student) {
        Date now = new Date();
        student.setCreateTime(now);
        student.setUpdateTime(now);
        student.setStatus((short) 0);
        studentCertifyMapper.insert(student);
    }

    @Override
    public void insert(EmployeeCertify employee) {
        Date now = new Date();
        employee.setCreateTime(now);
        employee.setUpdateTime(now);
        employee.setStatus((short) 0);
        employeeCertifyMapper.insert(employee);
    }
}
