package com.cainiaoshixi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class WorkExperience {
    private String company;

    private String department;

//    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date workBeginDate;

    private Date workEndDate;

    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}