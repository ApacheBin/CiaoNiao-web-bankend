package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.JobSubmit;
import java.util.List;

public interface JobSubmitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobSubmit record);

    JobSubmit selectByPrimaryKey(Integer id);

    List<JobSubmit> selectAll();

    int updateByPrimaryKey(JobSubmit record);

    Integer updateSelective(JobSubmit record);

    Integer insertSelective(JobSubmit record);

}