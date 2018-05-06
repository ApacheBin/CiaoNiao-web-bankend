package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.Job;
import com.cainiaoshixi.vo.JobQueryVo;

import java.util.List;

public interface JobMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Job record);

    Job selectByPrimaryKey(Integer id);

    List<Job> selectAll();

    int updateByPrimaryKey(Job record);

    List<Job> getJobListByVo(JobQueryVo jobQueryVo);

    int insertSelective(Job job);

    int updateByPrimaryKeySelective(Job job);
}