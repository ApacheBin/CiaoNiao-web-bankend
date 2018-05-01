package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Work;
import com.cainiaoshixi.service.IWorkService;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/work")
@CrossOrigin
@Api(value = "工作经历控制器", tags = {"实习/工作经验接口"})
@ResponseBody
public class WorkController {

    private final IWorkService workService;

    /**
     * 当前会话
     */
    private final SessionUtil session;

    @Autowired
    public WorkController(IWorkService workService, SessionUtil session) {
        this.workService = workService;
        this.session = session;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ApiOperation("获取工作经验")
    public Result getWork(@ApiParam(name = "id", value = "工作经验ID")
                              @RequestParam(value = "id", required = false) Integer id) {
        if(id != null) {
            return getWorkById(id);
        } else{
            return ResultUtil.success(workService.getByUserId(session.userId()));
        }
    }

    @GetMapping("/get/{id}")
    @ApiOperation("根据ID获取工作经验")
    public Result getWorkById(@ApiParam(name = "id", value = "工作经验ID", required = true)
                                  @PathVariable("id") Integer id) {
        return ResultUtil.success(workService.getById(id));
    }

    @PostMapping("/add")
    @ApiOperation("新增工作经历")
    public Result addWork(@RequestBody Work work) {
        work.setUserId(session.userId());
        workService.insert(work); //这个返回的是新增的记录数
        // 返回工作ID
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("workId", work.getId());
        return ResultUtil.success(jsonObject);
    }

    @PostMapping("/update")
    @ApiOperation("更新工作经历")
    public Result updateWork(@RequestBody Work work) {
        workService.update(work);
        return ResultUtil.success("");
    }

    @PostMapping("/delete")
    @ApiOperation("删除工作经历")
    public Result deleteWork(@RequestParam("id") int id) {
        workService.delete(id);
        return ResultUtil.success("");
    }
}
