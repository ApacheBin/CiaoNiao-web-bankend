package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.WorkExperience;

import java.util.List;

public interface WorkExperienceMapper {
    int insert(WorkExperience record);

    int insertSelective(WorkExperience record);

    List<WorkExperience> getWorkExpListByUserId(String userId);
}