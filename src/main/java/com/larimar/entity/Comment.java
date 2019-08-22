package com.larimar.entity;

/**
 * @author Larimar
 * @time 2019/8/20 周二 17:49
 */
public class Comment {
    private Integer commentId; //评论id
    private Integer userId;   //评论用户id
    private String userName;//评论用户名
    private String userPhoto;//评论用户头像
    private Integer commentAim;//评论对象id
    private Integer comicId;//评论漫画id
    private Integer detailId;//评论章节id
    private String content;//评论内容
    private Integer status;//评论状态
    private String time;//评论时间

    public Comment() {
    }

    public Comment(Integer commentId, Integer userId, String userName, String userPhoto, Integer commentAim, Integer comicId, Integer detailId, String content, Integer status, String time) {
        this.commentId = commentId;
        this.userId = userId;
        this.userName = userName;
        this.userPhoto = userPhoto;
        this.commentAim = commentAim;
        this.comicId = comicId;
        this.detailId = detailId;
        this.content = content;
        this.status = status;
        this.time = time;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
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

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPhoto='" + userPhoto + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", time='" + time + '\'' +
                ", commentAim=" + commentAim +
                ", comicId=" + comicId +
                ", detailId=" + detailId +
                '}';
    }
}
