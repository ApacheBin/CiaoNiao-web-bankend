package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.File;
import com.cainiaoshixi.entity.JobSubmit;
import com.cainiaoshixi.entity.Resume;
import com.cainiaoshixi.util.PageUtil;
import com.cainiaoshixi.vo.JobBriefVo;
import com.cainiaoshixi.vo.ResumeDetailVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResumeMapper {

    Resume getEvaluationByUserId(int userId);

    public void addEvaluation(Resume resume);

    public void updateEvaluation(Resume resume);

    public Integer isExistEvaluation(Integer userId);

    public int submitJob(JobSubmit jobSubmit);

    public JobSubmit isJobSubmitted(@Param("userId") Integer userId, @Param("jobId") Integer jobId);

    public List<JobBriefVo> querySubmitByUserId(@Param("jobBriefVo")JobBriefVo jobBriefVo,@Param("pageSize")int pageSize,@Param("pageStart")int pageStart);

    public int queryCount(JobBriefVo jobBriefVo);

    public File isResumeUploaded(Integer userId);
}
