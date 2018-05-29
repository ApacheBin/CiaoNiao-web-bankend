package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.JobSubmit;
import com.cainiaoshixi.entity.Resume;
import com.cainiaoshixi.vo.JobBriefVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResumeMapper {

    Resume getEvaluationByUserId(int userId);

    public void addEvaluation(Resume resume);

    public void updateEvaluation(Resume resume);

    public Integer isExistEvaluation(Integer userId);

    public int submitJob(JobSubmit jobSubmit);

    public List<JobBriefVo> querySubmitByUserId(Integer userId);

    public JobSubmit isJobSubmitted(@Param("userId") Integer userId, @Param("jobId") Integer jobId);
}
