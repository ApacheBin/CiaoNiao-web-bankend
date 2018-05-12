package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.EducationMapper;
import com.cainiaoshixi.dao.ResumeMapper;
import com.cainiaoshixi.dao.WorkExperienceMapper;
import com.cainiaoshixi.dao.WxUserMapper;
import com.cainiaoshixi.entity.*;
import com.cainiaoshixi.service.IResumeService;
import com.cainiaoshixi.vo.JobBriefVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Chy
 * @Description:
 * @Date: Created at 0:57 2018/3/13
 */
@Service
public class ResumeServiceImpl implements IResumeService {

    @Autowired
    private WxUserMapper wxUserMapper;
    @Autowired
    private EducationMapper educationMapper;
    @Autowired
    private WorkExperienceMapper workExperienceMapper;
    @Autowired
    private ResumeMapper resumeMapper;

    @Override
    public void savePersonInfo(WxUser wxUser) {
        if (wxUserMapper.selectByPrimaryKey(wxUser.getUserId()) == null) {
            wxUserMapper.insertSelective(wxUser);
        } else {
            wxUserMapper.updateByPrimaryKeySelective(wxUser);
        }

    }

    @Override
    public void saveEducationExp(Education education) {
        educationMapper.insertSelective(education);
    }

    @Override
    public void saveWorkExp(WorkExperience workExperience) {
        workExperienceMapper.insertSelective(workExperience);
    }

    @Override
    public WxUser getUserByUserId(String userId) {
        return wxUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<Education> getEducationListByUserId(String userId) {
        return educationMapper.getEducationListByUserId(userId);
    }

    @Override
    public List<WorkExperience> getWorkExpListByUserId(String userId) {
        return workExperienceMapper.getWorkExpListByUserId(userId);
    }

    @Override
    public Resume getEvaluationByUserId(int uid) {
        return resumeMapper.getEvaluationByUserId(uid);
    }

    @Override
    public void addEvaluation(Resume resume) {
        Date now = new Date();
        resume.setCreateTime(now);
        resume.setUpdateTime(now);
        resumeMapper.addEvaluation(resume);
    }

    @Override
    public void updateEvaluation(Resume resume) {
        Date now = new Date();
        resume.setUpdateTime(now);
        resumeMapper.updateEvaluation(resume);
    }

    @Override
    public int isExistEvaluation(int userId) {
        return resumeMapper.isExistEvaluation(userId);
    }

    @Override
    public int submitJob(JobSubmit jobSubmit) {
        Date now = new Date();
        jobSubmit.setCreateTime(now);
        jobSubmit.setUpdateTime(now);
        return resumeMapper.submitJob(jobSubmit);
    }

    @Override
    public List<JobBriefVo> querySubmitByUserId(int userId){
        return resumeMapper.querySubmitByUserId(userId);
    }

}