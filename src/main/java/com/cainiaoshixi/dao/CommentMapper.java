package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.Comment;

import java.util.List;

public interface CommentMapper {
    /**
     *
     * @param record
     * @return
     */
    int insert(Comment record);

    /**
     *
     * @param record
     * @return
     */
    int insertSelective(Comment record);

    /**
     * 通过commentID查询
     * @param commentId
     * @return
     */
    Comment getCommentByCommentId(Integer commentId);

    /**
     * 通过userID查询
     * @param userId
     * @return
     */
    List<Comment> getCommentListByUserId(int userId);

    /**
     * 增加评论
     * @param comment
     */
    void addComment(Comment comment);

    /**
     * 更新评论
     * @param comment
     */
    void updateComment(Comment comment);

    /**
     * 投诉与建议处理
     * @param comment
     */
    void responseToComment(Comment comment);

    /**
     * 删除评论
     * @param commentId
     */
    void deleteComment(Integer commentId);
}
