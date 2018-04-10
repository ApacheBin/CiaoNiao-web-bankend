package cn.xiaobaiqiuzhi.admin.service;

import cn.xiaobaiqiuzhi.admin.entity.Education;
import cn.xiaobaiqiuzhi.admin.entity.WorkExperience;
import cn.xiaobaiqiuzhi.admin.entity.WxUser;

import java.util.List;

public interface IResumeService {
    
    void savePersonInfo(WxUser wxUser);

    void saveEducationExp(Education education);

    void saveWorkExp(WorkExperience workExperience);

    WxUser getUserByUserId(String userId);

    List<Education> getEducationListByUserId(String userId);

    List<WorkExperience> getWorkExpListByUserId(String userId);
}
