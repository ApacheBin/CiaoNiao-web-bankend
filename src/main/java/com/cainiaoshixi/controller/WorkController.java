package com.cainiaoshixi.controller;

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

    private final SessionUtil session;

    @Autowired
    public WorkController(IWorkService workService, SessionUtil session) {
        this.workService = workService;
        this.session = session;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ApiOperation("获取工作经验")
    public Result getWork(@ApiParam(name = "id", value = "工作经验ID") Integer id) {
        Integer userId = session.userId();
        return ResultUtil.success(getWorkById(id));

    }

    @GetMapping("/get/{id}")
    @ApiOperation("根据ID获取工作经验")
    public Result getWorkById(@ApiParam(name = "id", value = "工作经验ID", required = true)
                                  @PathVariable("id") Integer id) {
        return ResultUtil.success(workService.getById(id));
    }
}
