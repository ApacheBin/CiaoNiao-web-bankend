package com.cainiaoshixi.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Component;

@Component("CnJobBriefVo")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "查看简历投递情况（发布者）")
public class ResumeBriefVo {

    private  int jobId;

    private int submitId;

    private int userId;

    private String userName;

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
}
