package com.cainiaoshixi.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Component;

@Component("CnResumeBriefVo")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "查看简历投递情况（发布者）")
public class ResumeBriefVo {

    private  int jobId;

    private int submitId;

    private int userId;

    private int hrStatus;

    private int viewCount;

    private String userName;

    private int jobUserId;

    private String avatarUrl;

    private Byte sex;

    private String school;

    private String major;

    private String resumePath;

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getSubmitId() {
        return submitId;
    }

    public void setSubmitId(int submitId) {
        this.submitId = submitId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public int getHrStatus() {
        return hrStatus;
    }

    public void setHrStatus(int hrStatus) {this.hrStatus = hrStatus; }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {this.viewCount = viewCount; }

    public int getJobUserId() {
        return jobUserId;
    }

    public void setJobUserId(int jobUserId) {
        this.jobUserId = jobUserId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
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

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }
}
