package com.cainiaoshixi.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EducationVo {
    private String school;

    private String major;

    private int degree;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date admissionTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date graduationTime;

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getDegree() {
        return degree;
    }

    public Date getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(Date admissionTime) {
        this.admissionTime = admissionTime;
    }

    public Date getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Date graduationTime) {
        this.graduationTime = graduationTime;
    }
}
