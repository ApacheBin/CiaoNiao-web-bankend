package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.Comment;

import java.util.List;

public interface ICommentService {

    Comment getCommentByCommentId(int commentId);

    List<Comment> getCommentListByUserId(int userId);

    void addComment(Comment comment);

    void updateComment(Comment comment);

    void deleteComment(Integer commentId);

}
