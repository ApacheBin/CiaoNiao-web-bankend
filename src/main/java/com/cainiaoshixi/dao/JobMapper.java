package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.Job;
import com.cainiaoshixi.entity.JobWithLogo;
import com.cainiaoshixi.vo.JobQueryVo;
import com.cainiaoshixi.vo.ResumeBriefVo;
import com.cainiaoshixi.vo.ResumeDetailVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Job record);

    JobWithLogo selectByPrimaryKey(Integer id);

    List<Job> selectAll();

    int updateByPrimaryKey(Job record);

    List<JobWithLogo> getJobListByVo(JobQueryVo jobQueryVo);

    List<JobWithLogo> getJobListByUserId(JobQueryVo jobQueryVo);

    int insertSelective(Job job);

    int updateByPrimaryKeySelective(Job job);

    List<ResumeBriefVo> querySubmitByJobId(@Param("jobId")Integer jobId, @Param("hrStatus")Integer hrStatus,@Param("pageSize")Integer pageSize,@Param("pageStart")Integer pageStart);

    ResumeDetailVo querySubmitByResumeId(@Param("jobId")Integer jobId,@Param("submitId")Integer submitId,@Param("userId")Integer userId);
}