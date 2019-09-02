package com.larimar.selectPojo;

/**
 * @author Larimar
 * @time 2019/9/2 周一 12:56
 */
public class CommentSelect {
    private String userName;
    private Integer commentType;
    private Integer aimId;

    public CommentSelect() {
    }

    public CommentSelect(String userName, Integer commentType, Integer aimId) {
        this.userName = userName;
        this.commentType = commentType;
        this.aimId = aimId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public Integer getAimId() {
        return aimId;
    }

    public void setAimId(Integer aimId) {
        this.aimId = aimId;
    }

    @Override
    public String toString() {
        return "CommentSelect{" +
                "userName='" + userName + '\'' +
                ", commentType=" + commentType +
                ", aimId=" + aimId +
                '}';
    }
}
