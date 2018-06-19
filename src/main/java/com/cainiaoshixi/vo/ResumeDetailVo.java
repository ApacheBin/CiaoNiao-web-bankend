package com.cainiaoshixi.vo;

import com.cainiaoshixi.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component("CnJobBriefVo")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "查看非个人简历（发布者）")
public class ResumeDetailVo {

    private  int jobId;

    private int resumeId;

    private int submitId;

    private int submitUserId;

    private String evaluation;

    private User user;

    private List<Skill> skill;

    private List<Education> education;

    private List<SchoolExperience> schoolExperience;

    private List<Work> work;

    private boolean isResumeUploaded;

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public int getSubmitId() {
        return submitId;
    }

    public void setSubmitId(int submitId) {
        this.submitId = submitId;
    }

    public int getSubmitUserId() {
        return submitUserId;
    }

    public void setSubmitUserId(int submitUserId) {
        this.submitUserId = submitUserId;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public User getUser(){return user;}

    public void setUser(User user){
        this.user=user;
    }

    public List<Skill> getSkill() {
        return skill;
    }

    public void setSkill(List<Skill> skill) {
        this.skill = skill;
    }

    public List<Education> getEducation() { return education; }

    public void setEducation(List<Education> education) { this.education = education; }

    public List<SchoolExperience> getSchoolExperience() {
        return schoolExperience;
    }

    public void setSchoolExperience(List<SchoolExperience> schoolExperience) {
        this.schoolExperience = schoolExperience;
    }

    public List<Work> getWork() {
        return work;
    }

    public void setWork(List<Work> work) {
        this.work = work;
    }

    public boolean getIsResumeUploaded() {
        return isResumeUploaded;
    }

    public void setIsResumeUploaded(boolean isResumeUploaded) {
        this.isResumeUploaded = isResumeUploaded;
    }
}
