package cn.xiaobaiqiuzhi.admin.entity;

import java.util.Date;

public class Education {
    private String school;

    private String major;

    private String degree;

    private Date schoolBeginDate;

    private Date schoolEndDate;

    private String userId;

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

    public Date getSchoolBeginDate() {
        return schoolBeginDate;
    }

    public void setSchoolBeginDate(Date schoolBeginDate) {
        this.schoolBeginDate = schoolBeginDate;
    }

    public Date getSchoolEndDate() {
        return schoolEndDate;
    }

    public void setSchoolEndDate(Date schoolEndDate) {
        this.schoolEndDate = schoolEndDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}