package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.*;
import com.cainiaoshixi.entity.*;
import com.cainiaoshixi.service.IJobService;
import com.cainiaoshixi.util.PageUtil;
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
    private EducationMapper educationMapper;
    private WorkMapper workMapper;
    private SchoolExpMapper schoolExpMapper;

    public JobServiceImpl(EducationMapper educationMapper, WorkMapper workMapper, SchoolExpMapper schoolExpMapper) {
        this.educationMapper = educationMapper;
        this.workMapper = workMapper;
        this.schoolExpMapper = schoolExpMapper;
    }

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
    public PageUtil<ResumeBriefVo> querySubmitByJobId(ResumeBriefVo resumeBriefVo,int pageSize,int pageNumber){
        PageUtil<ResumeBriefVo> page = new PageUtil<ResumeBriefVo>();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        int reCount = queryCount(resumeBriefVo);
        page.setTotalDataCount(reCount);
        int pageStart=page.getStartRow();
        List<ResumeBriefVo> jobBriefVoList= cnjobMapper.querySubmitByJobId(resumeBriefVo,pageSize,pageStart);
        page.setList(jobBriefVoList);
        return page;
    }

    @Override
    public int queryCount(ResumeBriefVo resumeBriefVo){
        return cnjobMapper.queryCount(resumeBriefVo);
    }

    @Override
    public ResumeDetailVo querySubmitByResumeId(int jobId, int resumeId, int userId){
        ResumeDetailVo resumeDetailVo=cnjobMapper.querySubmitByResumeId(jobId,resumeId,userId);
        if(resumeDetailVo != null){
            List<Education> education=educationMapper.getEducationListByEduId(resumeDetailVo.getSubmitUserId(),-1);
            if(education != null)
                resumeDetailVo.setEducation(education);
            List<Work> workExperience = workMapper.queryByUserId(resumeDetailVo.getSubmitUserId());
            if(workExperience != null)
                resumeDetailVo.setWork(workExperience);
            List<SchoolExperience>  schoolExperience = schoolExpMapper.queryBySchId(userId,-1);
            if(schoolExperience != null)
                resumeDetailVo.setSchoolExperience(schoolExperience);
        }
        return resumeDetailVo;
    }

    @Override
    public int updateViewCount(Integer submitId){
        return cnjobMapper.updateViewCount(submitId);
    }
    @Override
    public int updateInterest(int jobId,int submitId,int userId){
        return cnjobMapper.updateInterest(jobId,submitId,userId);
    }

    @Override
    public int updateUnfit(int jobId,int submitId,int userId){
        return cnjobMapper.updateUnfit(jobId,submitId,userId);
    }
}
