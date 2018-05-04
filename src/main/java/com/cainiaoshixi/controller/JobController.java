package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.JobWithBLOBs;
import com.cainiaoshixi.service.IJobService;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import com.cainiaoshixi.vo.JobQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@CrossOrigin
@Api(value = "岗位管理控制器", tags = {"岗位管理接口"})
@ResponseBody
public class JobController {

    private IJobService jobService;
    /**
     * 当前会话
     */
    private final SessionUtil session;

    @Autowired
    public JobController(IJobService jobService,SessionUtil session) {
        this.jobService = jobService;
        this.session = session;
    }

    /**
     * @Author: Chy
     * @Modification：  Zxc
     * @Param:
     * @Description 条件查询
     * @Date: Created in 13:47 2018/1/31
     */
    @ApiOperation("条件查询岗位列表")
    @PostMapping("/jobList")
    public Result getJobList(@RequestBody JobQueryVo jobQueryVo) {
        jobQueryVo.setUserId(session.userId());
        JSONObject jsonObject = new JSONObject();
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
    @ApiOperation("根据id查看单条岗位详情")
    @GetMapping("/jobDetail/{id}")
    public Result getJobDetail(@ApiParam(name = "id", value = "岗位详情ID", required = true)
                                   @PathVariable Long id){
        JobWithBLOBs jobWithBLOBs = jobService.selectByPrimaryKey(id);
        return ResultUtil.success(jobWithBLOBs);
    }


    /**
     * @Author: Zxc
     * @Param:
     * @Description: 添加单条岗位
     * @Date: Created in 12:28 2018/4/24
     */
    @ApiOperation("添加单条岗位")
    @PostMapping("/insertJob")
    public Result insertJob(@RequestBody JobWithBLOBs jobWithBLOBs) {
        jobWithBLOBs.setUserId(session.userId());
        jobWithBLOBs.setCreatedTime(new Date());
        jobWithBLOBs.setUpdatedTime(new Date());
        if (jobWithBLOBs.getPublished() == null){
            jobWithBLOBs.setPublished((byte) 0);
        }
        jobService.insertJob(jobWithBLOBs); //这个返回的是新增的记录数
        // 返回岗位ID
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jobId", jobWithBLOBs.getId());
        return ResultUtil.success(jsonObject);
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 根据Id更新岗位详情
     * @Date: 12:49 2018/4/24
     */
    @ApiOperation("根据Id更新岗位详情")
    @PostMapping("/update")
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
    @ApiOperation("根据id删除单条岗位")
    @GetMapping("/delete/{id}")
    public Result deleteJob(@ApiParam(name = "id", value = "岗位ID", required = true)
                                @PathVariable Long id) {
        jobService.deleteByPrimaryKey(id);
        return ResultUtil.success("");
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 根据id设置发布单条岗位
     * @Date: 12:49 2018/4/24
     */
    @ApiOperation("根据id设置发布单条岗位")
    @GetMapping("/setJobPublished/{id}")
    public Result setJobPublished(@ApiParam(name = "id", value = "岗位ID", required = true)
                                         @PathVariable Long id) {
        jobService.updateJobPublished(id);
        return ResultUtil.success("");
    }





}


