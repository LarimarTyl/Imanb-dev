package com.larimar.entity;

/**
 * @author Larimar
 * @time 2019/8/20 周二 19:00
 */
public class Likes {
    private Integer likesId; //点赞id
    private Integer number; //点赞数

    public Likes() {
    }

    public Likes(Integer likesId, Integer number) {
        this.likesId = likesId;
        this.number = number;
    }

    public Integer getLikesId() {
        return likesId;
    }

    public void setLikesId(Integer likesId) {
        this.likesId = likesId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Likes{" +
                "likesId=" + likesId +
                ", number=" + number +
                '}';
    }
}
