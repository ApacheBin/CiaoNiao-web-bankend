package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Comment;
import com.cainiaoshixi.service.ICommentService;
import com.cainiaoshixi.util.RedisUtil;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comment")
@CrossOrigin
@Api(value = "投诉与建议控制器", tags = {"投诉与建议接口"})
@ResponseBody
public class CommentController {

    private final ICommentService commentService;

    private final SessionUtil session;

    @Autowired
    public CommentController(ICommentService commentService,SessionUtil session) {
        this.commentService=commentService;
        this.session = session;
    }

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询该用户的评论
     * @param userId
     * @return
     */
    @RequestMapping(value="/get", method = RequestMethod.POST)
    public Result getCommentListByUserId(@RequestParam(value = "userId", required = false,defaultValue = "-1") Integer  userId){
        userId = session.userId();
//        JSONObject jsonObject=new JSONObject();
        List<Comment> commentList=commentService.getCommentListByUserId(userId);
//        return ResultUtil.success(JSON.toJSONString(commentList,SerializerFeature.WriteMapNullValue));
        return ResultUtil.success(commentList);
    }
    /**
     * 保存用户的comment
     * @param comment
     * @return
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addComment(@RequestBody Comment comment){
        comment.setUserId(session.userId());
        commentService.addComment(comment);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CommentId", comment.getCommentId());
        return ResultUtil.success(jsonObject);
    }

    /**
     * 更新评论
     * @param comment
     * @return
     */
    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateComment(@RequestBody Comment comment){
        comment.setUserId(session.userId());
        commentService.updateComment(comment);
        return ResultUtil.success("");
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public Result deleteComment(@RequestParam("commentId") int  commentId){
        commentService.deleteComment(commentId);
        return ResultUtil.success("");
    }
}
