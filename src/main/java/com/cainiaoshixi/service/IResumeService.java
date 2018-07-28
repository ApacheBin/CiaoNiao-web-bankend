package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.*;
import com.cainiaoshixi.util.PageUtil;
import com.cainiaoshixi.vo.JobBriefVo;

import java.util.List;

public interface IResumeService {

    void savePersonInfo(WxUser wxUser);

    void saveEducationExp(Education education);

    void saveWorkExp(WorkExperience workExperience);

    WxUser getUserByUserId(String userId);

    List<Education> getEducationListByUserId(String userId);

    List<WorkExperience> getWorkExpListByUserId(String userId);

    Resume getEvaluationByUserId(int userId);

    public void addEvaluation(Resume resume);

    public void updateEvaluation(Resume resume);

    public int isExistEvaluation(int userId);

    public int submitJob(JobSubmit jobSubmit);

    public boolean isJobSubmitted(int userId,int jobId);

    public PageUtil<JobBriefVo> querySubmitByUserId(JobBriefVo jobBriefVo,int pageSize,int pageStart);

    public int queryCount(JobBriefVo jobBriefVo);

    public boolean resumeUploaded(int userId);

}
