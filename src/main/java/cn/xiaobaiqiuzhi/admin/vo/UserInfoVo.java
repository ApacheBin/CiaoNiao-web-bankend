package cn.xiaobaiqiuzhi.admin.vo;

import java.util.Date;

/**
 * @Author: Chy
 * @Description: 用来接受用户基本信息、教育经历、工作经历等信息
 * @Date: Created at 0:44 2018/3/13
 */
public class UserInfoVo {
    private String userId;

    private String name;

    private Integer sex;

    private Integer identity;

    private Date birth;

    private String region;

    private String email;

    private String phone;

    private String school;

    private String major;

    private String degree;

    private Date schoolBeginDate;

    private Date schoolEndDate;

    private String company;

    private String department;

    private Date workBeginDate;

    private Date workEndDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getWorkBeginDate() {
        return workBeginDate;
    }

    public void setWorkBeginDate(Date workBeginDate) {
        this.workBeginDate = workBeginDate;
    }

    public Date getWorkEndDate() {
        return workEndDate;
    }

    public void setWorkEndDate(Date workEndDate) {
        this.workEndDate = workEndDate;
    }

}