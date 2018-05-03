package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.Education;

import java.util.List;

public interface IEducationService {
    List<Education> getEducationListByEduId(int userId, int eduId);

    void addEducation(Education education);

    void updateEducation(Education education);

    void deleteEducation(int id);
}
