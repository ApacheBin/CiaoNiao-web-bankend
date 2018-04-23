package com.cainiaoshixi.controller;

import com.cainiaoshixi.service.IJobService;
import com.cainiaoshixi.vo.JobQueryVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.entity.JobWithBLOBs;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: Chy
 * @Description: 岗位相关
 * @Date: Created at 21:51 2018/1/29
 */
@Controller
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private IJobService jobService;

    /**
     * @Author: Chy
     * @Param:
     * @Description 条件查询
     * @Date: Created in 13:47 2018/1/31
     */
    @ResponseBody
    @RequestMapping(value = "/jobList", produces = "application/json; charset=utf-8")
    public String getJobList(@RequestBody JobQueryVo jobQueryVo) {

        JSONObject jsonObject = new JSONObject();
        List<JobWithBLOBs> jobList = jobService.getJobListByVo(jobQueryVo);  //条件查询
        return JSON.toJSONString(jobList, SerializerFeature.WriteMapNullValue);  //null值保留
    }

}


