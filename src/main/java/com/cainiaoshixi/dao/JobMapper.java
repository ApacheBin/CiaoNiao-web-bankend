package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.Job;
import com.cainiaoshixi.entity.JobWithBLOBs;
import com.cainiaoshixi.vo.JobQueryVo;

import java.util.List;

public interface JobMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JobWithBLOBs record);

    int insertSelective(JobWithBLOBs record);

    JobWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JobWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(JobWithBLOBs record);

    int updateByPrimaryKey(Job record);

    List<JobWithBLOBs> getJobListByVo(JobQueryVo jobQueryVo);
}