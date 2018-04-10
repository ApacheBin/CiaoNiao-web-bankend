package cn.xiaobaiqiuzhi.admin.service;

import cn.xiaobaiqiuzhi.admin.entity.JobWithBLOBs;
import cn.xiaobaiqiuzhi.admin.vo.JobQueryVo;

import java.util.List;

public interface IJobService {
    List<JobWithBLOBs> getJobListByVo(JobQueryVo jobQueryVo);
}
