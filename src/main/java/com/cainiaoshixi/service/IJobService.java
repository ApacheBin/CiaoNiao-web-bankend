package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.Job;
import com.cainiaoshixi.entity.JobWithLogo;
import com.cainiaoshixi.vo.JobQueryVo;
import com.cainiaoshixi.vo.ResumeBriefVo;
import com.cainiaoshixi.vo.ResumeDetailVo;

import java.util.List;

public interface IJobService {
    List<JobWithLogo> getJobListByVo(JobQueryVo jobQueryVo);

    List<JobWithLogo> getJobListByUserId(JobQueryVo jobQueryVo);

    JobWithLogo selectByPrimaryKey(Integer id);

    int insertJob(Job job);

    int updateById(Job job);

    int deleteByPrimaryKey(Integer id);

    int updateJobStatus(Integer id);

    List<ResumeBriefVo> querySubmitByJobId(int jobId,int hrStatus,int pageSize,int pageStart);

    ResumeDetailVo querySubmitByResumeId(int jobId, int submitId, int userId);
}
