package com.cainiaoshixi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("CnJobSubmit")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "简历投递实体")
public class JobSubmit {
    public int id;

    private  int jobId;

    private int status;

    private int userId;

    private int hrStatus;

    private int viewCount;

    private byte emailViewed;

    public byte getEmailViewed() {
        return emailViewed;
    }

    public void setEmailViewed(byte emailViewed) {
        this.emailViewed = emailViewed;
    }

    private Date createTime;

    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {this.status = status; }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHrStatus() {
        return hrStatus;
    }

    public void setHrStatus(int hrStatus) {this.hrStatus = hrStatus; }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {this.viewCount = viewCount; }

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
