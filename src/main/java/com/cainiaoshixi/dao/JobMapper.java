package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.Job;
import com.cainiaoshixi.entity.JobWithOther;
import com.cainiaoshixi.vo.EmailVo;
import com.cainiaoshixi.vo.JobQueryVo;
import com.cainiaoshixi.vo.ResumeBriefVo;
import com.cainiaoshixi.vo.ResumeDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobMapper {
    int deleteByPrimaryKey(Integer id);

    JobWithOther selectByPrimaryKey(Integer id);

    List<Job> selectAll();

    int updateByPrimaryKey(Job record);

    List<JobWithOther> getJobListByVo(JobQueryVo jobQueryVo);

    List<JobWithOther> getJobListByUserId(Integer userId);

    int insertSelective(Job job);

    int updateByPrimaryKeySelective(Job job);

    List<ResumeBriefVo> querySubmitByJobId(@Param("resumeBriefVo")ResumeBriefVo resumeBriefVo,@Param("pageSize")Integer pageSize,@Param("pageStart")Integer pageStart);

    int queryCount(ResumeBriefVo resumeBriefVo);

    ResumeDetailVo querySubmitByResumeId(@Param("jobId")Integer jobId,@Param("userId")Integer userId,@Param("jobUserId")Integer jobUserId);

    int updateViewCount(Integer submitId);

    int updateInterest(@Param("jobId")Integer jobId,@Param("userId")Integer userId,@Param("jobUserId")Integer jobUserId);

    int updateUnfit(@Param("jobId")Integer jobId,@Param("userId")Integer userId,@Param("jobUserId")Integer jobUserId);

    EmailVo selectEmail(int jobId);
}