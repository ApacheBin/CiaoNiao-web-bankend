package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Comment;
import com.cainiaoshixi.service.ICommentService;
import com.cainiaoshixi.util.FileUtil;
import com.cainiaoshixi.util.RedisUtil;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/comment")
@CrossOrigin
@Api(value = "投诉与建议控制器", tags = {"投诉与建议接口"})
@ResponseBody
public class CommentController {

    private final static String COMMENT_IMAGE_DIR = "/data/images/comments/";

    private final static String IMAGE_URL = "cainiaoshixi.com/images/comments/";

    private final ICommentService commentService;

    private final SessionUtil session;

    @Autowired
    public CommentController(ICommentService commentService,
                             SessionUtil session) {
        this.commentService=commentService;
        this.session = session;
    }

    /**
     * 查询该用户的评论
     * @param userId
     * @return
     */
    @RequestMapping(value="/get", method = RequestMethod.GET)
    public Result getCommentListByUserId(@RequestParam(value = "userId",required = false,defaultValue = "-1") Integer  userId){
        userId = session.userId();
        List<Comment> commentList=commentService.getCommentListByUserId(userId);
        String location = null;
        for(Comment comment: commentList) {
            String imageLocation = null;
            location =comment.getImageLocation();
            String[] locationArray= location.split(";");
            for (int i = 0; i < locationArray.length; i++) {
                locationArray[i] = IMAGE_URL +locationArray[i];
                if(imageLocation==null){
                    imageLocation = locationArray[i];
                }else {
                    imageLocation = imageLocation + ";" + locationArray[i];
                }
            }
            comment.setImageLocation(imageLocation);
        }
        return ResultUtil.success(commentList);
    }

    /**
     *
     * @param commentId
     * @return
     */
    @RequestMapping(value="/getByCommentId", method = RequestMethod.GET)
    public Result getCommentByCommentId(@RequestParam(value = "commentId") Integer  commentId){
        //userId = session.userId();
        Comment comment=commentService.getCommentByCommentId(commentId);
        String location = null;
        String imageLocation = null;
        location =comment.getImageLocation();
        String[] locationArray= location.split(";");
        for (int j = 0; j < locationArray.length; j++) {
            locationArray[j] = IMAGE_URL +locationArray[j];
            if(imageLocation==null){
                imageLocation = locationArray[j];
            }else {
                imageLocation = imageLocation + ";" + locationArray[j];
                }
            }
            comment.setImageLocation(imageLocation);
        return ResultUtil.success(comment);
    }
    /**
     * 添加投诉与建议
     * @param commentType
     * @param userComment
     * @param images
     * @param email
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ApiOperation("添加投诉建议")
    public Result addComment(@RequestParam("commentType") Byte commentType,
                             @RequestParam("userComment") String userComment,
                             @RequestParam("images") MultipartFile[] images,
                             @RequestParam(value="email", required = false) String email) throws IOException {
        String imageName= null;
        String relativePath = null;
        if(null !=images && images.length>0){
            for(MultipartFile image : images){
                    if((imageName== null) && (relativePath == null)){
                        imageName=image.getOriginalFilename();
                        relativePath =FileUtil.getRelativePath(image);
                        FileUtil.save(image, COMMENT_IMAGE_DIR + relativePath);
                    }else{
                        imageName = imageName + ";" + image.getOriginalFilename();
                        relativePath = relativePath + ";" + FileUtil.getRelativePath(image);
                        FileUtil.save(image, COMMENT_IMAGE_DIR + FileUtil.getRelativePath(image));
                    }

                }
            }
        Comment comment = new Comment();
        comment.setUserId(session.userId());
        comment.setCommentType(commentType);
        comment.setUserComment(userComment);
        comment.setImageName(imageName);
        comment.setImageLocation(relativePath);
        comment.setEmail(email);
        commentService.addComment(comment);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("commentId", comment.getCommentId());
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

    @RequestMapping(value="/response", method = RequestMethod.POST)
    public Result responseToComment(@RequestParam("status") Byte status,
                                    @RequestParam("response") String response,
                                    @RequestParam(value = "commentId") Integer  commentId){
        Comment comment =commentService.getCommentByCommentId(commentId);
        comment.setStatus(status);
        comment.setResponse(response);
        commentService.responseToComment(comment);
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
