package com.larimar.entity;

/**
 * @author Larimar
 * @time 2019/8/20 周二 19:05
 */
public class Orders {
    private Integer ordersId;//订阅表id
    private Integer comicId;//漫画id
    private String comicName;//漫画名
    private Integer userId;//用户id
    private String userName;//用户名
    private Integer status;//订阅状态（0未更新 1已有更新 -1已读更新）

    public Orders() {
    }

    public Orders(Integer ordersId, Integer comicId, String comicName, Integer userId, String userName, Integer status) {
        this.ordersId = ordersId;
        this.comicId = comicId;
        this.comicName = comicName;
        this.userId = userId;
        this.userName = userName;
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

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
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
                ", comicName='" + comicName + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", status=" + status +
                '}';
    }
}
