package com.cainiaoshixi.controller;

import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.service.IJobService;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.vo.JobQueryVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.entity.JobWithBLOBs;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: Chy Zxc
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
     * @Modification：  Zxc
     * @Param:
     * @Description 条件查询
     * @Date: Created in 13:47 2018/1/31
     */
    @ResponseBody
    @RequestMapping(value = "/jobList", produces = "application/json; charset=utf-8",method = {RequestMethod.POST,RequestMethod.GET})
    public Result getJobList(@RequestBody JobQueryVo jobQueryVo) {

//        JSONObject jsonObject = new JSONObject();
        List<JobWithBLOBs> jobList = jobService.getJobListByVo(jobQueryVo);  //条件查询
//        return JSON.toJSONString(jobList, SerializerFeature.WriteMapNullValue);  //null值保留
        return ResultUtil.success(jobList);
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description 根据id查看单条岗位详情
     * @Date: Created in 12:28 2018/4/24
     */
    @ResponseBody
    @RequestMapping(value = "/get/{id}",method = {RequestMethod.POST,RequestMethod.GET})
    public Result getJobDetail(@PathVariable Long id){
        JobWithBLOBs jobWithBLOBs = jobService.selectByPrimaryKey(id);
        return ResultUtil.success(jobWithBLOBs);
    }


    /**
     * @Author: Zxc
     * @Param:
     * @Description: 添加单条岗位
     * @Date: Created in 12:28 2018/4/24
     */
    @ResponseBody
    @RequestMapping("/add")
    public Result insertJob(@RequestBody JobWithBLOBs jobWithBLOBs) {
        jobWithBLOBs.setCreatedTime(new Date());
        jobWithBLOBs.setUpdatedTime(new Date());
        if (jobWithBLOBs.getPublished() == null){
            jobWithBLOBs.setPublished((byte) 0);
        }
        jobService.insertJob(jobWithBLOBs);
        return ResultUtil.success("");
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 根据Id更新岗位详情
     * @Date: 12:49 2018/4/24
     */
    @ResponseBody
    @RequestMapping("/update")
    public Result updateJob(@RequestBody JobWithBLOBs jobWithBLOBs) {
        jobWithBLOBs.setUpdatedTime(new Date());
        if (jobWithBLOBs.getPublished() == null){
            jobWithBLOBs.setPublished((byte) 0);
        }
        jobService.updateById(jobWithBLOBs);
        return ResultUtil.success("");
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 根据id删除单条岗位
     * @Date: 12:49 2018/4/24
     */
    @ResponseBody
    @RequestMapping(value="/delete/{id}",method = {RequestMethod.POST,RequestMethod.GET})
    public Result deleteJob(@PathVariable Long id) {
        jobService.deleteByPrimaryKey(id);
        return ResultUtil.success("");
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 根据id设置发布单条岗位
     * @Date: 12:49 2018/4/24
     */
    @ResponseBody
    @RequestMapping(value="/post/{id}",method = {RequestMethod.POST,RequestMethod.GET})
    public Result updateJobPublished(@PathVariable Long id) {
        jobService.updateJobPublished(id);
        return ResultUtil.success("");
    }





}


