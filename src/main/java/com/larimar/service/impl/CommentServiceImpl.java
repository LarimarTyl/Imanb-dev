package com.larimar.service.impl;

import com.larimar.entity.Comment;
import com.larimar.mapper.CommentMapper;
import com.larimar.selectPojo.CommentSelect;
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
    public boolean updateComment(Comment comment) {
        return commentMapper.updateComment(comment)>0;
    }

    @Override
    public boolean updateCommentStatus(Integer commentId, Integer userId,Integer status) {
        return commentMapper.updateCommentStatus(commentId, userId, status)>0;
    }

    @Override
    public boolean updateStatus(Integer commentId, Integer status) {
        return commentMapper.updateStatus(commentId,status)>0;
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
    public List<Comment> selectByOption(CommentSelect commentSelect) {
        return commentMapper.selectByOptions(commentSelect);
    }

    @Override
    public List<Comment> getUsersComment(Integer userId) {
        List<Comment> comments = commentMapper.selectUsersComments(userId);
        for (Comment c: comments) {
            c.setAimComment(commentMapper.selectCommentById(c.getCommentAim()));
        }
        return comments;
    }

    @Override
    public List<Comment> getUsersRevertByStatus(Integer userId, Integer status) {
        List<Comment> comments = commentMapper.selectRevertsByStatus(userId, status);
        for (Comment c: comments) {
            c.setAimComment(commentMapper.selectCommentById(c.getCommentAim()));
        }
        return comments;
    }

    @Override
    public List<Comment> getUsersRevertComment(Integer userId) {
        List<Comment> comments = commentMapper.selectUsersRevertComments(userId);
        for (Comment c: comments) {
            c.setAimComment(commentMapper.selectCommentById(c.getCommentAim()));
        }
        return comments;
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
