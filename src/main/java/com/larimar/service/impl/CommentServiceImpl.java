package com.larimar.service.impl;

import com.larimar.entity.Comment;
import com.larimar.mapper.CommentMapper;
import com.larimar.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/26 周一 13:00
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.addComment(comment)>0;
    }

    @Override
    public boolean addDetailComment(Comment comment) {
        return commentMapper.addDetailComment(comment)>0;
    }

    @Override
    public boolean addComicComment(Comment comment) {
        return commentMapper.addComicComment(comment)>0;
    }

    @Override
    public boolean addRevertComment(Comment comment) {
        return commentMapper.addRevertComment(comment)>0;
    }

    @Override
    public boolean delComment(Integer commentId) {
        return commentMapper.delComment(commentId)>0;
    }

    @Override
    public boolean delUserComment(Integer commentId,Integer userId) {
        return commentMapper.delUserComment(commentId,userId)>0;
    }

    @Override
    public boolean updateCommentStatus(Integer commentId, Integer userId,Integer status) {
        return commentMapper.updateCommentStatus(commentId, userId, status)>0;
    }

    @Override
    public Comment getCommentById(Integer commentId) {
        return commentMapper.selectCommentById(commentId);
    }

    @Override
    public List<Comment> getAllComment() {
        return commentMapper.queryAllComments();
    }

    @Override
    public List<Comment> getUsersComment(Integer userId) {
        return commentMapper.selectUsersComments(userId);
    }

    @Override
    public List<Comment> getUsersCommentByStatus(Integer userId, Integer status) {
        return commentMapper.selectCommentsByStatus(userId, status);
    }

    @Override
    public List<Comment> getUsersRevertComment(Integer userId) {
        return commentMapper.selectUsersRevertComments(userId);
    }

    @Override
    public List<Comment> getComicComment(Integer comicId) {
        return commentMapper.selectComicComments(comicId);
    }

    @Override
    public List<Comment> getDetailComment(Integer detailId) {
        return commentMapper.selectDetailComments(detailId);
    }

    @Override
    public List<Comment> getRevertComment(Integer commentId) {
        return commentMapper.selectRevertComments(commentId);
    }

}
