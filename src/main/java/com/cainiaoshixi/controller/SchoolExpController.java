package com.cainiaoshixi.controller;
import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.SchoolExperience;
import com.cainiaoshixi.service.ISchoolExpService;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/school/experience")
@CrossOrigin
@Api(value = "校园经历控制器", tags = {"校园经历接口"})
@ResponseBody
public class SchoolExpController {
    private final ISchoolExpService schoolExpService;

    /**
     * 当前会话
     */
    private final SessionUtil session;

    @Autowired
    public SchoolExpController(ISchoolExpService schoolExpService, SessionUtil session) {
        this.schoolExpService = schoolExpService;
        this.session = session;
    }
    @ResponseBody
    @RequestMapping(value = "/get",method= RequestMethod.GET)
    public Result getSchoolExpListBySchId(@RequestParam(value = "id", required = false,defaultValue = "-1") Integer schId){
        Integer userId = session.userId();
        List<SchoolExperience> schoolExpList = schoolExpService.queryBySchId(userId,schId);  //条件查询
        return ResultUtil.success(schoolExpList);
    }

    @PostMapping("/add")
    @ApiOperation("新增校园经历")
    public Result addSchoolExp(@RequestBody SchoolExperience schoolExperience) {
        schoolExperience.setUserId(session.userId());
        schoolExpService.insert(schoolExperience);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("SchoolExpId", schoolExperience.getId());
        return ResultUtil.success(jsonObject);
    }

    @PostMapping("/update")
    @ApiOperation("更新校园经历")
    public Result updateSchoolExp(@RequestBody SchoolExperience schoolExperience) {
        schoolExpService.update(schoolExperience);
        return ResultUtil.success("");
    }

    @PostMapping("/delete")
    @ApiOperation("删除校园经历")
    public Result deleteSchoolExp(@RequestParam("id") int id){
        schoolExpService.delete(id);
        return ResultUtil.success("");
    }
}
