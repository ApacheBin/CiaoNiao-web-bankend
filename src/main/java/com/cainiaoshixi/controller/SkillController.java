package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Skill;
import com.cainiaoshixi.service.ISkillService;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/skill")
@CrossOrigin
@Api(value = "技能证书控制器", tags = {"技能证书接口"})
@ResponseBody
public class SkillController {
    private final ISkillService skillService;
    /**
     * 当前会话
     */
    private final SessionUtil session;

    @Autowired
    public SkillController(ISkillService skillService, SessionUtil session) {
        this.skillService = skillService;
        this.session = session;
    }

    @PostMapping("/save")
    @ApiOperation("保存技能证书")
    public Result saveSkill(@RequestBody Skill skill) {
        Integer userId=session.userId();
        skill.setUserId(userId);
        int exist=skillService.isExistSkill(userId);
        if(exist>0)
            skillService.updateByUserId(skill);
        else if(exist==0) {
            skillService.insert(skill);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("skillId", skill.getId());
            return ResultUtil.success(jsonObject);
        }
        return ResultUtil.success("");
    }
    @GetMapping("/get")
    @ApiOperation("根据ID获取技能证书")
    @ResponseBody
    public Result getSkillByUserId(){
        Integer userId = session.userId();
        return ResultUtil.success(skillService.selectByUserId(userId));
    }
}
