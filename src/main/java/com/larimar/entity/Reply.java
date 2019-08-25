package com.larimar.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/22 周四 14:08
 * 抽象每个评论楼层为一个评论对象 包含评论所有的内容
 * 还有一个子评论数组（如果只有一层 则为顶楼层 子评论为空）
 */
public class Reply {

    private Integer commentId; //评论id
    private User user;//评论用户对象
    private Integer commentAim;//评论对象id
    private Integer comicId;//评论漫画id
    private Integer detailId;//评论章节id
    private String content;//评论内容
    private Integer status;//评论状态
    private String time;//评论时间
    private List<Reply> replyList;//回复对象

    public Reply() {
    }

    /**
     * 顶层构造方法
     * 无回复楼
     * @param comment 评论对象
     */
    public Reply(Comment comment) {
        this.commentId = comment.getCommentId();
        this.user = comment.getUser();
        this.commentAim = comment.getCommentAim();
        this.comicId = comment.getComicId();
        this.detailId = comment.getDetailId();
        this.content = comment.getContent();
        this.status = comment.getStatus();
        this.time = comment.getTime();
        this.replyList = new LinkedList<>();
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCommentAim() {
        return commentAim;
    }

    public void setCommentAim(Integer commentAim) {
        this.commentAim = commentAim;
    }

    public Integer getComicId() {
        return comicId;
    }

    public void setComicId(Integer comicId) {
        this.comicId = comicId;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "commentId=" + commentId +
                ", user=" + user +
                ", commentAim=" + commentAim +
                ", comicId=" + comicId +
                ", detailId=" + detailId +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", time='" + time + '\'' +
                ", replyList=" + replyList +
                '}';
    }
}
