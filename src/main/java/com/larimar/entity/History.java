package com.larimar.entity;

/**
 * @author Larimar
 * @time 2019/8/20 周二 17:56
 */
public class History {
    private Integer historyId;//阅读历史id
    private Integer userId;//用户id
    private String userName;//用户名
    private Integer comicId;//漫画id
    private String comicName;//漫画名
    private Integer detailId;//章节id
    private String chapterName;//章节名
    private String lastReadTime;//最后一次阅读时间
    private Integer status;//状态 0未更新 1已有更新 -1已读更新

    public History() {
    }

    public History(Integer historyId, Integer userId, String userName, Integer comicId, String comicName, Integer detailId, String chapterName, String lastReadTime, Integer status) {
        this.historyId = historyId;
        this.userId = userId;
        this.userName = userName;
        this.comicId = comicId;
        this.comicName = comicName;
        this.detailId = detailId;
        this.chapterName = chapterName;
        this.lastReadTime = lastReadTime;
        this.status = status;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
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

    public Integer getComicId() {
        return comicId;
    }

    public void setComicId(Integer comicId) {
        this.comicId = comicId;
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public String getchapterName() {
        return chapterName;
    }

    public void setchapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getLastReadTime() {
        return lastReadTime;
    }

    public void setLastReadTime(String lastReadTime) {
        this.lastReadTime = lastReadTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "History{" +
                "historyId=" + historyId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", comicId=" + comicId +
                ", comicName='" + comicName + '\'' +
                ", detailId=" + detailId +
                ", chapterName='" + chapterName + '\'' +
                ", lastReadTime='" + lastReadTime + '\'' +
                ", status=" + status +
                '}';
    }
}
