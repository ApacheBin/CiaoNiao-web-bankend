package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Reward;
import com.cainiaoshixi.service.IRewardService;
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
@RequestMapping("/reward")
@CrossOrigin
@Api(value = "荣誉与奖励控制器", tags = {"荣誉与奖励接口"})
@ResponseBody
public class RewardController {

    private final IRewardService rewardService;

    /**
     * 当前会话
     */
    private final SessionUtil session;

    @Autowired
    public RewardController(IRewardService rewardService, SessionUtil session) {
        this.rewardService = rewardService;
        this.session = session;
    }

    @RequestMapping(value = "/get", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation("获取荣誉记录")
    public Result getReward(@RequestParam(value = "id", required = false,defaultValue = "-1") Integer rewardId) {
        Integer userId = session.userId();
        List<Reward> rewardList = rewardService.getReward(userId,rewardId);  //条件查询
        return ResultUtil.success(rewardList);
    }
    @PostMapping("/add")
    @ApiOperation("新增荣誉记录")
    public Result addReward(@RequestBody Reward reward) {
        reward.setUserId(session.userId());
        rewardService.addReward(reward); //这个返回的是新增的记录数
        // 返回荣誉记录ID
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rewardId", reward.getId());
        return ResultUtil.success(jsonObject);
    }

    @PostMapping("/update")
    @ApiOperation("更新荣誉记录")
    public Result updateReward(@RequestBody Reward reward) {
        rewardService.updateReward(reward);
        return ResultUtil.success("");
    }

    @PostMapping("/delete")
    @ApiOperation("删除荣誉记录")
    public Result deleteReward(@RequestParam("id") int id) {
        rewardService.deleteReward(id);
        return ResultUtil.success("");
    }
}
