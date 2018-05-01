package com.cainiaoshixi.vo;

import java.util.Date;

public class EducationVo {
    private String school;

    private String major;

    private String degree;

    private Date admissionTime;

    private Date graduationTime;

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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Date getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(Date schoolBeginDate) {
        this.admissionTime = admissionTime;
    }

    public Date getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Date schoolEndDate) {
        this.graduationTime = graduationTime;
    }
}
