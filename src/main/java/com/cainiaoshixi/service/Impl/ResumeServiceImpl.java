package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.EducationMapper;
import com.cainiaoshixi.dao.WorkExperienceMapper;
import com.cainiaoshixi.dao.WxUserMapper;
import com.cainiaoshixi.entity.Education;
import com.cainiaoshixi.entity.WorkExperience;
import com.cainiaoshixi.entity.WxUser;
import com.cainiaoshixi.service.IResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Chy
 * @Description:
 * @Date: Created at 0:57 2018/3/13
 */
@Service
public class ResumeServiceImpl implements IResumeService{

    @Autowired
    private WxUserMapper wxUserMapper;
    @Autowired
    private EducationMapper educationMapper;
    @Autowired
    private WorkExperienceMapper workExperienceMapper;

    @Override
    public void savePersonInfo(WxUser wxUser) {
        if(wxUserMapper.selectByPrimaryKey(wxUser.getUserId()) == null){
            wxUserMapper.insertSelective(wxUser);
        }else{
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
}