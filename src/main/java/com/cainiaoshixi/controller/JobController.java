package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Job;
import com.cainiaoshixi.entity.JobWithLogo;
import com.cainiaoshixi.service.IEducationService;
import com.cainiaoshixi.service.IFileService;
import com.cainiaoshixi.service.IJobService;
import com.cainiaoshixi.util.PageUtil;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import com.cainiaoshixi.vo.JobQueryVo;
import com.cainiaoshixi.vo.ResumeBriefVo;
import com.cainiaoshixi.vo.ResumeDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/job")
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
    public JobController(IJobService jobService, SessionUtil session, IFileService fileService) {
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
    @PostMapping("/list")
    public Result getJobList(@RequestBody JobQueryVo jobQueryVo) {
//        JSONObject jsonObject = new JSONObject();
        List<JobWithLogo> jobList = jobService.getJobListByVo(jobQueryVo);  //条件查询
//        return JSON.toJSONString(jobList, SerializerFeature.WriteMapNullValue);  //null值保留
        return ResultUtil.success(jobList);
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description 根据用户id查询岗位列表
     * @Date: Created in 12:28 2018/4/24
     */
    @ApiOperation("根据用户id条件查询岗位列表")
    @PostMapping("/mylist")
    public Result getMyList(@RequestBody JobQueryVo jobQueryVo){
        jobQueryVo.setUserId(session.userId());
        List<JobWithLogo> jobList = jobService.getJobListByUserId(jobQueryVo);
        return ResultUtil.success(jobList);
    }


    /**
     * @Author: Zxc
     * @Param:
     * @Description 根据id查看单条岗位详情
     * @Date: Created in 12:28 2018/4/24
     */
    @ApiOperation("根据id查看单条岗位详情")
    @PostMapping("/detail")
    public Result getJobDetail(@RequestParam(value = "id", required = true) Integer id){
        JobWithLogo jobDetail = jobService.selectByPrimaryKey(id);
//        int jobReadCount=0;
//        if(session.jobReadCount()!=null){
//            jobReadCount = session.jobReadCount();
//        }
//        jobReadCount++;
//        session.setJobReadCount(jobReadCount);
        JobWithLogo jobDetailCopy = new JobWithLogo();
        jobDetailCopy.setId(id);
        jobDetailCopy.setReadCount(jobDetail.getReadCount()+1);
        jobService.updateById(jobDetailCopy);

        return ResultUtil.success(jobDetail);
    }


    /**
     * @Author: Zxc
     * @Param:
     * @Description: 添加单条岗位
     * @Date: Created in 12:28 2018/4/24
     */
    @ApiOperation("添加单条岗位")
    @PostMapping("/add")
    public Result addJob(@RequestBody Job singleJob) {
        singleJob.setUserId(session.userId());
        singleJob.setCreateTime(new Date());
        singleJob.setUpdateTime(new Date());
        if (singleJob.getStatus() == null){
            singleJob.setStatus((byte) 0);
        }
        jobService.insertJob(singleJob); //这个返回的是新增的记录数
        // 返回岗位ID
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jobId",singleJob.getId());
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
    public Result updateJob(@RequestBody Job singleJob) {
        singleJob.setUpdateTime(new Date());
        if (singleJob.getStatus() == null){
            singleJob.setStatus((byte) 0);
        }
        jobService.updateById(singleJob);
        return ResultUtil.success("");
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 根据id删除单条岗位
     * @Date: 12:49 2018/4/24
     */
    @ApiOperation("根据id删除单条岗位")
    @PostMapping("/delete")
    public Result deleteJob(@RequestParam(value = "id", required = true) Integer id) {
        jobService.deleteByPrimaryKey(id);
        return ResultUtil.success("");
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 根据id设置单条岗位发布状态
     * @Date: 12:49 2018/4/24
     */
    @ApiOperation("根据id设置发布单条岗位")
    @PostMapping("/status/set")
    public Result setJobStatus(@RequestParam(value = "id", required = true) Integer id) {
        jobService.updateJobStatus(id);
        return ResultUtil.success("");
    }

    @GetMapping("/resume/list")
    @ApiOperation("获取简历列表")
    public Result getSubmitListByJobId(@RequestParam("jobId") int jobId,@RequestParam(value="hrStatus",required = false,defaultValue = "0")int hrStatus, @RequestParam("pageNumber")int pageNumber, @RequestParam("pageSize")int pageSize){
        int userId=session.userId();
        ResumeBriefVo resumeBriefVo=new ResumeBriefVo();
        resumeBriefVo.setUserId(userId);
        resumeBriefVo.setJobId(jobId);
        resumeBriefVo.setHrStatus(hrStatus);
        PageUtil<ResumeBriefVo> resumeBriefVos = jobService.querySubmitByJobId(resumeBriefVo,pageSize,pageNumber);  //条件查询
        return ResultUtil.success(resumeBriefVos);
    }

    @GetMapping("/resume/get")
    @ApiOperation("获取非个人简历")
    public Result getResumeByResId(@RequestParam("jobId") int jobId,@RequestParam("resumeId")int resumeId){
        ResumeDetailVo resumeDetailVo= jobService.querySubmitByResumeId(jobId,resumeId,session.userId());  //条件查询
        if(resumeDetailVo != null){
            jobService.updateViewCount(resumeDetailVo.getSubmitId());
        }
        return ResultUtil.success(resumeDetailVo);
    }

    @PostMapping("/resume/interest")
    @ApiOperation("发布者对某个简历感兴趣")
    public Result updateInterest(@RequestParam("jobId") int jobId,@RequestParam("submitId") int submitId){
        int userId=session.userId();
        return ResultUtil.success(jobService.updateInterest(jobId,submitId,userId));
    }

    @PostMapping("/resume/unfit")
    @ApiOperation("发布者对某个简历不感兴趣")
    public Result updateUnfit(@RequestParam("jobId") int jobId,@RequestParam("submitId") int submitId){
        int userId=session.userId();
        return ResultUtil.success(jobService.updateUnfit(jobId,submitId,userId));
    }
}


