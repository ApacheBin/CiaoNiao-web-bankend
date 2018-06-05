package com.cainiaoshixi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("CnJob")
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) //可用但已过期
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "岗位管理实体")
public class Job {
    private Integer id;

    private String name;

    private String company;

    private Integer companyId;

    private String department;

    private Byte type;

    private String province;

    private String city;

    private String district;

    private String address;

    private Integer industry;

    private Integer function;

    private Byte isRemote;

    private String description;

    private String requirement;

    private String salary;

    private String welfare;

    private Short dutyPerWeek;

    private Integer duration;

    private Integer userId;

    private Short receiveMethod;

    private String receiveEmail;

    private Byte eduRequired;

    private Byte vertifyResult;

    private Byte status;

    private Byte becomeFull;

    private Integer readCount;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public Integer getFunction() {
        return function;
    }

    public void setFunction(Integer function) {
        this.function = function;
    }

    public Byte getIsRemote() {
        return isRemote;
    }

    public void setIsRemote(Byte isRemote) {
        this.isRemote = isRemote;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public Short getDutyPerWeek() {
        return dutyPerWeek;
    }

    public void setDutyPerWeek(Short dutyPerWeek) {
        this.dutyPerWeek = dutyPerWeek;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Short getReceiveMethod() {
        return receiveMethod;
    }

    public void setReceiveMethod(Short receiveMethod) {
        this.receiveMethod = receiveMethod;
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    public Byte getEduRequired() {
        return eduRequired;
    }

    public void setEduRequired(Byte eduRequired) {
        this.eduRequired = eduRequired;
    }

    public Byte getVertifyResult() {
        return vertifyResult;
    }

    public void setVertifyResult(Byte vertifyResult) {
        this.vertifyResult = vertifyResult;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getBecomeFull() {
        return becomeFull;
    }

    public void setBecomeFull(Byte becomeFull) {
        this.becomeFull = becomeFull;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
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