package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.CnJobMapper;
import com.cainiaoshixi.entity.CnJob;
import com.cainiaoshixi.service.IJobService;
import com.cainiaoshixi.vo.JobQueryVo;
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
    private CnJobMapper cnjobMapper;

    public List<CnJob> getJobListByVo(JobQueryVo jobQueryVo) {
        return cnjobMapper.getJobListByVo(jobQueryVo);
    }

    public CnJob selectByPrimaryKey(Integer id) {
        return cnjobMapper.selectByPrimaryKey(id);
    }

    public int insertJob(CnJob job) {
       return cnjobMapper.insertSelective(job);
    }

    public int updateById(CnJob job) {
        return cnjobMapper.updateByPrimaryKeySelective(job);
    }

    public int deleteByPrimaryKey(Integer id) {
        return cnjobMapper.deleteByPrimaryKey(id);
    }

    public int updateJobStatus(Integer id) {
        CnJob job = new CnJob();
        job.setId(id);
        job.setStatus((byte) 1);
        return cnjobMapper.updateByPrimaryKeySelective(job);
    }
}
