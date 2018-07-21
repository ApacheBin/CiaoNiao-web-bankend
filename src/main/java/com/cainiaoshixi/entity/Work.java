package com.cainiaoshixi.entity;

import com.cainiaoshixi.validation.groups.WhenAdd;
import com.cainiaoshixi.validation.groups.WhenUpdate;
import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Component("CnWork")
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) //可用但已过期
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "工作经验实体")
public class Work {
    @NotNull(message = "主键不能为空", groups = {WhenUpdate.class})
    private Integer id;

    @NotBlank(message = "公司名称不能为空", groups = {WhenAdd.class})
    @Length(max = 500, message = "公司名称过长")
    private String company;

    @NotBlank(message = "部门名称不能为空", groups = {WhenAdd.class})
    @Length(max = 500, message = "部门名称过长")
    private String department;

    @NotBlank(message = "岗位名称不能为空", groups = {WhenAdd.class})
    @Length(max = 500, message = "部门名称过长")
    private String position;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "日期不能超过今天")
    private Date entryTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "日期不能超过今天")
    private Date departureTime;

    @NotBlank(message = "工作内容不能为空", groups = {WhenAdd.class})
    private String content;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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
