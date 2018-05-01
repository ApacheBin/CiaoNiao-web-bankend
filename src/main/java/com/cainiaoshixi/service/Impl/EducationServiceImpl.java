package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.EducationMapper;
import com.cainiaoshixi.entity.Education;
import com.cainiaoshixi.service.IEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class EducationServiceImpl implements IEducationService {
    @Autowired
    private EducationMapper educationMapper;

    @Override
    public List<Education> getEducationListByEduId(int userId, int eduId){
        return educationMapper.getEducationListByEduId(userId,eduId);
    }
    @Override
    public void addEducation(Education education){
        education.setCreateTime(new Date());
        education.setUpdateTime(new Date());
        educationMapper.addEducation(education);
    }
    @Override
    public void updateEducation(Education education){ educationMapper.updateEducation(education);}
    @Override
    public void deleteEducation(int eduId){
        educationMapper.deleteEducation(eduId);
    }
}
