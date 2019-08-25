package com.larimar.entity;

/**
 * @author Larimar
 * @time 2019/8/20 周二 19:05
 */
public class Orders {
    private Integer ordersId;//订阅表id
    private Integer comicId;//漫画id
    private Comic comic;//漫画对象
    private Integer userId;//用户id
    private User user;//用户对象
    private Integer status;//订阅状态（0未更新 1已有更新 -1已读更新）

    public Orders() {
    }

    public Orders(Integer ordersId, Integer comicId, Comic comic, Integer userId, User user, Integer status) {
        this.ordersId = ordersId;
        this.comicId = comicId;
        this.comic = comic;
        this.userId = userId;
        this.user = user;
        this.status = status;
    }

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "ordersId=" + ordersId +
                ", comicId=" + comicId +
                ", comic=" + comic +
                ", userId=" + userId +
                ", user=" + user +
                ", status=" + status +
                '}';
    }
}
