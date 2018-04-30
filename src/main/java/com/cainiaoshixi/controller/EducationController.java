package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Education;
import com.cainiaoshixi.service.IResumeService;
import com.cainiaoshixi.util.RedisUtil;
import com.cainiaoshixi.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/education")
public class EducationController {
    @Autowired
    private IResumeService resumeService;
    private RedisUtil redisUtil = new RedisUtil();
    /**
     * @Author: Yzh
     * @Param:
     * @Description: 查询用户的教育经历
     * @Date: 17:11 2018/4/23
     */
    @RequestMapping(value="/get",method= RequestMethod.GET)
    public Result getEducationListByEduId(@RequestParam("id")int eduId, @RequestParam("sessionId")String sessionId) {
        int userId = Integer.parseInt((String) redisUtil.get(sessionId));
        JSONObject jsonObject = new JSONObject();
        List<Education> educationList = resumeService.getEducationListByEduId(userId,eduId);  //条件查询
        return ResultUtil.success(JSON.toJSONString(educationList, SerializerFeature.WriteMapNullValue));//null值保留
    }

    /**
     * @Author: Yzh
     * @Param:
     * @Description: 保存用户的教育经历
     * @Date: 17:20 2018/4/23
     */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    @ResponseBody
    public Result addEducation(@RequestBody Education education) {
        resumeService.saveEducationExp(education);
        return ResultUtil.success("");
    }

    /**
     * @Author: Yzh
     * @Param:
     * @Description: 更新用户的教育经历
     * @Date: 17:31 2018/4/23
     */
    @RequestMapping(value = "/update",method= RequestMethod.POST)
    @ResponseBody
    public Result updateEducation(@RequestBody Education education) {
        resumeService.updateEducation(education);
        return ResultUtil.success("");
    }

    /**
     * @Author: Yzh
     * @Param:
     * @Description: 删除用户的教育经历
     * @Date: 17:31 2018/4/23
     */
    @RequestMapping(value="/delete" ,method= RequestMethod.POST)
    public Result deleteEducation(@RequestParam int id) {
        resumeService.deleteEducation(id);
        return ResultUtil.success("");
    }
}
