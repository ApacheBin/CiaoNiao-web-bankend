package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.JobWithBLOBs;
import com.cainiaoshixi.vo.JobQueryVo;

import java.util.List;

public interface IJobService {
    List<JobWithBLOBs> getJobListByVo(JobQueryVo jobQueryVo);
}
