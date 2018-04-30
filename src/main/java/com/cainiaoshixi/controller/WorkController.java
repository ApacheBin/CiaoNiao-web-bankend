package com.cainiaoshixi.controller;

import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Work;
import com.cainiaoshixi.service.IWorkService;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/work")
@CrossOrigin
public class WorkController {

    private final IWorkService workService;

    private final SessionUtil session;

    @Autowired
    public WorkController(IWorkService workService, SessionUtil session) {
        this.workService = workService;
        this.session = session;
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result getWork(@RequestParam("id") Integer id) {
        Integer userId = session.userId();
        Work work = workService.getById(id);
        return ResultUtil.success(work);

    }
}
