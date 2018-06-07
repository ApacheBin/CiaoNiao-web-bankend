package com.cainiaoshixi.vo;

import com.cainiaoshixi.entity.Education;
import com.cainiaoshixi.entity.SchoolExperience;
import com.cainiaoshixi.entity.Skill;
import com.cainiaoshixi.entity.WorkExperience;
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

    private int userId;

    private String evaluation;

    private String skill;

    private String education;

    private String schoolExperience;

    private String workExperience;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getEducation() { return education; }

    public void setEducation(String education) { this.education = education; }

    public String getSchoolExperience() {
        return schoolExperience;
    }

    public void setSchoolExperience(String schoolExperience) {
        this.schoolExperience = schoolExperience;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }
}
