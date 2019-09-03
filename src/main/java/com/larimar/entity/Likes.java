package com.larimar.entity;

/**
 * @author Larimar
 * @time 2019/8/20 周二 19:00
 */
public class Likes {
    private Integer likesId;
    private Integer comicId;
    private Integer detailId; //点赞id
    private Integer number; //点赞数

    public Likes() {
    }

    public Likes(Integer likes_id, Integer comicId, Integer detailId, Integer number) {
        this.likesId = likes_id;
        this.comicId = comicId;
        this.detailId = detailId;
        this.number = number;
    }

    public Integer getLikesId() {
        return likesId;
    }

    public void setLikesId(Integer likesId) {
        this.likesId = likesId;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Likes{" +
                "likes_id=" + likesId +
                ", comicId=" + comicId +
                ", detailId=" + detailId +
                ", number=" + number +
                '}';
    }
}
