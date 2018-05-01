package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.Education;
import com.cainiaoshixi.entity.WorkExperience;
import com.cainiaoshixi.entity.WxUser;

import java.util.List;

public interface IResumeService {

    void savePersonInfo(WxUser wxUser);

    void saveEducationExp(Education education);

    void saveWorkExp(WorkExperience workExperience);

    WxUser getUserByUserId(String userId);

    List<Education> getEducationListByUserId(String userId);

    List<WorkExperience> getWorkExpListByUserId(String userId);

}
