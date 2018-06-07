package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.JobMapper;
import com.cainiaoshixi.entity.Job;
import com.cainiaoshixi.entity.JobWithLogo;
import com.cainiaoshixi.service.IJobService;
import com.cainiaoshixi.vo.JobQueryVo;
import com.cainiaoshixi.vo.ResumeBriefVo;
import com.cainiaoshixi.vo.ResumeDetailVo;
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
    private JobMapper cnjobMapper;

    public List<JobWithLogo> getJobListByVo(JobQueryVo jobQueryVo) {
        return cnjobMapper.getJobListByVo(jobQueryVo);
    }

    public List<JobWithLogo> getJobListByUserId(JobQueryVo jobQueryVo) {
        return cnjobMapper.getJobListByUserId(jobQueryVo);
    }

    public JobWithLogo selectByPrimaryKey(Integer id) {
        return cnjobMapper.selectByPrimaryKey(id);
    }

    public int insertJob(Job job) {
       return cnjobMapper.insertSelective(job);
    }

    public int updateById(Job job) {
        return cnjobMapper.updateByPrimaryKeySelective(job);
    }

    public int deleteByPrimaryKey(Integer id) {
        return cnjobMapper.deleteByPrimaryKey(id);
    }

    public int updateJobStatus(Integer id) {
        Job job = new Job();
        job.setId(id);
        job.setStatus((byte) 1);
        return cnjobMapper.updateByPrimaryKeySelective(job);
    }
    @Override
    public List<ResumeBriefVo> querySubmitByJobId(int jobId,int hrStatus,int pageSize,int pageStart){
        return cnjobMapper.querySubmitByJobId(jobId,hrStatus,pageSize,pageStart);
    }

    @Override
    public ResumeDetailVo querySubmitByResumeId(int jobId, int submitId, int userId){
        return cnjobMapper.querySubmitByResumeId(jobId,submitId,userId);
    }
}
