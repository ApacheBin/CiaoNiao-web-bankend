package com.cainiaoshixi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component("CnEducation")
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) //可用但已过期
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "教育经历实体")
public class Education {
    public int id;

    private String school;

    private String major;

    private int degree;

    private Date admissionTime;

    private Date graduationTime;

    private int userId;

    private Date createTime;

    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setDegree(int degree) {
        this.degree = degree;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}