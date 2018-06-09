package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.CommentMapper;
import com.cainiaoshixi.entity.Comment;
import com.cainiaoshixi.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements ICommentService {
   @Autowired
   private CommentMapper commentMapper;

    @Override
    public Comment getCommentByCommentId(int commentId) {
        return commentMapper.getCommentByCommentId(commentId);
    }

    @Override
    public List<Comment> getCommentListByUserId(int userId) {
        return commentMapper.getCommentListByUserId(userId);
    }

    @Override
    public void addComment(Comment comment) {
        comment.setCreatedTime(new Date());
        comment.setUpdatedTime(new Date());
        commentMapper.addComment(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        comment.setUpdatedTime(new Date());
        commentMapper.updateComment(comment);
    }

    @Override
    public void responseToComment(Comment comment) {
        commentMapper.responseToComment(comment);
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentMapper.deleteComment(commentId);
    }
}
