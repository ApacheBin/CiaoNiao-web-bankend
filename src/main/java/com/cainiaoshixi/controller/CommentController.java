package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Comment;
import com.cainiaoshixi.service.ICommentService;
import com.cainiaoshixi.util.RedisUtil;
import com.cainiaoshixi.util.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/comment")
@CrossOrigin
@Api(value = "投诉与建议控制器", tags = {"投诉与建议接口"})
@ResponseBody
public class CommentController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询该用户的评论
     * @param userId
     * @return
     */
    @RequestMapping(value="/getComment", method = RequestMethod.GET)
    @ResponseBody
    public Result getCommentListByUserId(int  userId){
//        JSONObject jsonObject=new JSONObject();
        List<Comment> commentList=commentService.getCommentListByUserId(userId);
        return ResultUtil.success(JSON.toJSONString(commentList,SerializerFeature.WriteMapNullValue));
    }
    /**
     * 保存用户的comment
     * @param comment
     * @return
     */
    @RequestMapping(value="/addComment", method = RequestMethod.POST)
    @ResponseBody
    public Result addComment(@RequestBody Comment comment){
        comment.setCreatedTime(new Date());
        comment.setUpdatedTime(new Date());
        commentService.addComment(comment);
        return ResultUtil.success("");
    }

    /**
     * 更新评论
     * @param comment
     * @return
     */
    @RequestMapping(value="/updateComment", method = RequestMethod.POST)
    @ResponseBody
    public Result updateComment(@RequestBody Comment comment){
        comment.setUpdatedTime(new Date());
        commentService.updateComment(comment);
        return ResultUtil.success("");
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @RequestMapping(value="/deleteComment", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteComment(@RequestBody int commentId){
        commentService.deleteComment(commentId);
        return ResultUtil.success("");
    }
}
