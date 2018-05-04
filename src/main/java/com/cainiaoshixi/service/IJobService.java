package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.JobWithBLOBs;
import com.cainiaoshixi.vo.JobQueryVo;

import java.util.List;

public interface IJobService {
    List<JobWithBLOBs> getJobListByVo(JobQueryVo jobQueryVo);

    JobWithBLOBs selectByPrimaryKey(Long id);

    int insertJob(JobWithBLOBs jobWithBLOBs);

    int updateById(JobWithBLOBs jobWithBLOBs);

    int deleteByPrimaryKey(Long id);

    int updateJobPublished(Long id);
}
