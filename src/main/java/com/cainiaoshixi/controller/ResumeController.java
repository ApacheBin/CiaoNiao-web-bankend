package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Education;
import com.cainiaoshixi.entity.JobWithBLOBs;
import com.cainiaoshixi.entity.WorkExperience;
import com.cainiaoshixi.entity.WxUser;
import com.cainiaoshixi.service.IResumeService;
import com.cainiaoshixi.util.RedisUtil;
import com.cainiaoshixi.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author: Chy
 * @Description: 简历相关
 * @Date: Created at 11:33 2018/3/12
 */
@Controller
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private IResumeService resumeService;
    private RedisUtil redisUtil = new RedisUtil();

    /**
     * @Author: Chy
     * @Param:
     * @Description: 编辑简历
     * @Date: 11:36 2018/3/12
     */
    @RequestMapping("/editResume")
    public String editResume(Model model, @RequestParam String userId) {

        WxUser wxUser = resumeService.getUserByUserId(userId);
        List<Education> educationList = resumeService.getEducationListByUserId(userId);
        List<WorkExperience> workExperienceList = resumeService.getWorkExpListByUserId(userId);

        model.addAttribute("wxUser", wxUser);
        model.addAttribute("educationList", educationList);
        model.addAttribute("workExperienceList", workExperienceList);

        return "editResume";
    }

    /**
     * @Author: Chy
     * @Param:
     * @Description: 保存用户基本信息
     * @Date: 0:45 2018/3/13
     */
    @RequestMapping("/savePersonInfo")
    @ResponseBody
    public String savePersonInfo(@RequestBody WxUser wxUser) {
        resumeService.savePersonInfo(wxUser);
        return "success";
    }

    /**
     * @Author: Chy
     * @Param:
     * @Description: 保存用户的教育经历
     * @Date: 1:13 2018/3/13
     */
    @RequestMapping("/saveEducationExp")
    @ResponseBody
    public String saveEducationExp(@RequestBody List<Education> educationList) {
        for (Education education : educationList) {
            resumeService.saveEducationExp(education);
        }
        return "success";
    }

    @RequestMapping("/saveWorkExp")
    @ResponseBody
    public String saveWorkExp(@RequestBody List<WorkExperience> workExperienceList) {

        for (WorkExperience workExperience : workExperienceList){
            resumeService.saveWorkExp(workExperience);
        }
        return "success";
    }
}