package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.Education;

import java.util.List;

public interface EducationMapper {
    int insert(Education record);

    int insertSelective(Education record);

    List<Education> getEducationListByUserId(String userId);
}