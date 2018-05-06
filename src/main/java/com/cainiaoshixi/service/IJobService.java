package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.Job;
import com.cainiaoshixi.vo.JobQueryVo;

import java.util.List;

public interface IJobService {
    List<Job> getJobListByVo(JobQueryVo jobQueryVo);

    Job selectByPrimaryKey(Integer id);

    int insertJob(Job job);

    int updateById(Job job);

    int deleteByPrimaryKey(Integer id);

    int updateJobStatus(Integer id);
}
