package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.EmployeeCertifyMapper;
import com.cainiaoshixi.dao.StudentCertifyMapper;
import com.cainiaoshixi.entity.EmployeeCertify;
import com.cainiaoshixi.entity.StudentCertify;
import com.cainiaoshixi.service.IIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    public StudentCertify getStudentCertificationByUid(Integer userId) {
        return studentCertifyMapper.selectByUid(userId);
    }

    @Override
    public EmployeeCertify getEmployeeCertificationByUid(Integer userId) {
        return employeeCertifyMapper.selectByUid(userId);
    }

    @Override
    public List<StudentCertify> listStuCert(Integer page) {

        return studentCertifyMapper.selectAll((page - 1) * 20, 20);
    }

    @Override
    public List<EmployeeCertify> listEmployeeCert(Integer page) {
        return employeeCertifyMapper.selectAll((page - 1) * 20, 20);
    }

    @Override
    public Boolean existEmployeeCert(Integer userId) {
        return employeeCertifyMapper.selectByUid(userId) != null;
    }

    @Override
    public Boolean existStuCert(Integer userId) {
        return studentCertifyMapper.selectByUid(userId) != null;
    }

    @Override
    public void updateByUid(StudentCertify student) {
        Date now = new Date();
        student.setUpdateTime(now);
        studentCertifyMapper.updateByUid(student);
    }

    @Override
    public void updateByUid(EmployeeCertify employee) {
        Date now = new Date();
        employee.setUpdateTime(now);
        employeeCertifyMapper.updateByUid(employee);
    }
}
