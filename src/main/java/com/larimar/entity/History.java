package com.larimar.entity;

/**
 * @author Larimar
 * @time 2019/8/20 周二 17:56
 */
public class History {
    private Integer historyId;//阅读历史id
    private Integer userId;//用户id
    private User user;//用户对象
    private Integer comicId;//漫画id
    private Comic comic;//漫画对象
    private Integer detailId;//章节id
    private Detail detail;//章节对象
    private String lastReadTime;//最后一次阅读时间
    private Integer status;//状态 0未更新 1已有更新 -1已读更新

    public History() {
    }

    public History(Integer historyId, Integer userId, User user, Integer comicId, Comic comic, Integer detailId, Detail detail, String lastReadTime, Integer status) {
        this.historyId = historyId;
        this.userId = userId;
        this.user = user;
        this.comicId = comicId;
        this.comic = comic;
        this.detailId = detailId;
        this.detail = detail;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getComicId() {
        return comicId;
    }

    public void setComicId(Integer comicId) {
        this.comicId = comicId;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
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
                ", user=" + user +
                ", comicId=" + comicId +
                ", comic=" + comic +
                ", detailId=" + detailId +
                ", detail=" + detail +
                ", lastReadTime='" + lastReadTime + '\'' +
                ", status=" + status +
                '}';
    }
}
