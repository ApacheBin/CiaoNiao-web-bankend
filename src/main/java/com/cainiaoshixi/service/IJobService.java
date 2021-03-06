package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.Job;
import com.cainiaoshixi.entity.JobWithOther;
import com.cainiaoshixi.util.PageUtil;
import com.cainiaoshixi.vo.EmailVo;
import com.cainiaoshixi.vo.JobQueryVo;
import com.cainiaoshixi.vo.ResumeBriefVo;
import com.cainiaoshixi.vo.ResumeDetailVo;

import java.util.List;

public interface IJobService {
    List<JobWithOther> getJobListByVo(JobQueryVo jobQueryVo);

    List<JobWithOther> getJobListByUserId(Integer userId);

    JobWithOther selectByPrimaryKey(Integer id);

    int insertJob(Job job);

    int updateById(Job job);

    int deleteByPrimaryKey(Integer id);

    int updateJobStatus(Integer id);

    PageUtil<ResumeBriefVo> querySubmitByJobId(ResumeBriefVo resumeBriefVo, int pageSize, int pageStart);

    public int queryCount(ResumeBriefVo resumeBriefVo);

    ResumeDetailVo querySubmitByResumeId(int jobId, int userId, int jobUserId);

    int updateViewCount(Integer submitId);

    int updateInterest(int jobId,int userId,int jobUserId);

    int updateUnfit(int jobId,int userId,int jobUserId);

    EmailVo queryReceiveEmail(int jobId);
}
