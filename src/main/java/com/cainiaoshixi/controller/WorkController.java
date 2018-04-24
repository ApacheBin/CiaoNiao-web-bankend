package com.cainiaoshixi.controller;

import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.CnWork;
import com.cainiaoshixi.service.IWorkService;
import com.cainiaoshixi.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private IWorkService workService;

    @RequestMapping("/get")
    @ResponseBody
    public Result getWork(@RequestParam("sessionId") String sessionId, @RequestParam("id") Integer id) {
        CnWork work = workService.getById(id);
        return ResultUtil.success(work);

    }
}
