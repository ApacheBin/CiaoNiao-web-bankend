package cn.xiaobaiqiuzhi.admin.dao;

import cn.xiaobaiqiuzhi.admin.entity.Job;
import cn.xiaobaiqiuzhi.admin.entity.JobWithBLOBs;
import cn.xiaobaiqiuzhi.admin.vo.JobQueryVo;

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