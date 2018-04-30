package com.cainiaoshixi.controller;

import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Work;
import com.cainiaoshixi.service.IWorkService;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/work")
@CrossOrigin
@ResponseBody
public class WorkController {

    private final IWorkService workService;

    private final SessionUtil session;

    @Autowired
    public WorkController(IWorkService workService, SessionUtil session) {
        this.workService = workService;
        this.session = session;
    }

    @RequestMapping("/get")
    public Result getWork(@RequestParam(value = "id", required = false) Integer id) {
        Integer userId = session.userId();
        return getWorkById(id);

    }

    @RequestMapping("/get/{id}")
    public Result getWorkById(@PathVariable Integer id) {
        return ResultUtil.success(workService.getById(id));
    }
}
