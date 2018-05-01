package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.Education;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EducationMapper {
    Integer insert(Education record);

    Integer insertSelective(Education record);

    List<Education> getEducationListByUserId(String userId);

    List<Education> getEducationListByEduId(@Param("userId") Integer userId, @Param("eduId") Integer eduId);

    void addEducation(Education education);

    void updateEducation(Education education);

    void deleteEducation(Integer eduId);
}