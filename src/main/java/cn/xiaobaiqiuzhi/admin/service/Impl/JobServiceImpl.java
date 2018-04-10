package cn.xiaobaiqiuzhi.admin.service.Impl;

import cn.xiaobaiqiuzhi.admin.dao.JobMapper;
import cn.xiaobaiqiuzhi.admin.entity.JobWithBLOBs;
import cn.xiaobaiqiuzhi.admin.service.IJobService;
import cn.xiaobaiqiuzhi.admin.vo.JobQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:陈汉宇
 * @Description:
 * @Date: Created in 16:10 2018/1/30
 * @Modified By:
 */
@Service
public class JobServiceImpl implements IJobService {
    @Autowired
    private JobMapper jobMapper;

    public List<JobWithBLOBs> getJobListByVo(JobQueryVo jobQueryVo) {
        return jobMapper.getJobListByVo(jobQueryVo);
    }
}
