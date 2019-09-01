package com.larimar.service;

import com.larimar.entity.Comment;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/22 周四 20:42
 */
public interface CommentService {
    public boolean addComment(Comment comment);
    public boolean addDetailComment(Comment comment);
    public boolean addComicComment(Comment comment);
    public boolean addRevertComment(Comment comment);
    public boolean delComment(Integer commentId);
    public boolean delUserComment(Integer commentId,Integer userId);
    public boolean updateCommentStatus(Integer commentId,Integer userId,Integer status);
    public Comment getCommentById(Integer commentId);
    public List<Comment> getAllComment();
    public List<Comment> getUsersComment(Integer userId);
    public List<Comment> getUsersCommentByStatus(Integer userId,Integer status);
    public List<Comment> getUsersRevertComment(Integer userId);
    public List<Comment> getComicComment(Integer comicId);
    public List<Comment> getDetailComment(Integer detailId);
    public List<Comment> getRevertComment(Integer commentId);
}
