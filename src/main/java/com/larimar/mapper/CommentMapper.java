package com.larimar.mapper;

import com.larimar.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/20 周二 19:28
 */
public interface CommentMapper {
    public int addComment(Comment comment);
    /**
     * 添加对漫画评论
     * @param comment 评论对象 包含 漫画id
     * @return 返回评论数量
     */
    public int addComicComment(Comment comment);

    /**
     * 添加对章节的评论
     * @param comment 评论对象包含 漫画章节id
     * @return 评论数量
     */
    public int addDetailComment(Comment comment);

    /**
     * 添加回复评论
     * @param comment 评论对象包含 回复评论id
     * @return 返回添加数量
     */
    public int addRevertComment(Comment comment);

    /**
     * 删除评论
     * @param commentId 评论id
     * @param userId 用户id
     * @return 删除数量
     */
    public int delUserComment(@Param("commentId") Integer commentId,@Param("userId") Integer userId);
    public int delComment(Integer commentId);

    /**
     * 修改评论状态
     * @param commentId 评论表id
     * @param userId 用户id
     * @return 修改数量
     */
    public int updateCommentStatus(@Param("commentId") Integer commentId,@Param("userId") Integer userId,@Param("status")Integer status);

    /**
     * 查询用户所有的评论
     * @param userId
     * @return 评论集合
     */
    public List<Comment> selectUsersComments(Integer userId);
    public List<Comment> queryAllComments();
    /**
     * 按状态查询用户评论
     * @param userId 用户id
     * @param status 状态码
     * @return 评论集合
     */
    public List<Comment> selectCommentsByStatus(@Param("userId") Integer userId,@Param("status")Integer status);

    /**
     * 查找漫画对应的评论
     * @param comicId 漫画id
     * @return 评论集合
     */
    public  List<Comment> selectComicComments(Integer comicId);

    /**
     * 查找章节对应的评论
     * @param detailId 章节id
     * @return 评论对象
     */
    public List<Comment> selectDetailComments(Integer detailId);

    public Comment selectCommentById(Integer commentId);
    /**
     * 查找回复评论
     * @param commentId 回复对象评论id
     * @return 回复评论集合
     */
    public List<Comment> selectRevertComments(Integer commentId);

    /**
     * 查找用户回复评论
     * @param userId 用户id
     * @return 回复评论集合
     */
    public List<Comment> selectUsersRevertComments(Integer userId);
}
