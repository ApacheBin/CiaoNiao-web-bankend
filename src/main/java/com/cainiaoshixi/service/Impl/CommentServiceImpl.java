package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.CommentMapper;
import com.cainiaoshixi.entity.Comment;
import com.cainiaoshixi.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        commentMapper.addComment(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        commentMapper.updateComment(comment);
    }

    @Override
    public void deleteComment(int commentId) {
        commentMapper.deleteComment(commentId);
    }
}
