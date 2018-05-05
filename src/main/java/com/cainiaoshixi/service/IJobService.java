package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.CnJob;
import com.cainiaoshixi.vo.JobQueryVo;

import java.util.List;

public interface IJobService {
    List<CnJob> getJobListByVo(JobQueryVo jobQueryVo);

    CnJob selectByPrimaryKey(Integer id);

    int insertJob(CnJob job);

    int updateById(CnJob job);

    int deleteByPrimaryKey(Integer id);

    int updateJobStatus(Integer id);
}
