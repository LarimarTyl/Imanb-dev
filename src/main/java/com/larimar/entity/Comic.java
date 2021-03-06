package com.larimar.entity;

/**
 * @author Larimar
 * @time 2019/8/20 周二 17:37
 */
public class Comic {
    private Integer comicId;//漫画id
    private String comicName;//漫画名
    private String root;//漫画路径地址
    private String author;//作者
    private Integer status;//状态 1连载中 0完结 -1停更
    private String type; //类型名
    private String location; //地区（国漫、日漫）
    private String newUpdate; //最新更新时间
    private String newChapterName;//最新章节
    private String introduction; //简介
    private Double mark; //评分
    private Integer likes;

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Comic() {
    }

    public Comic(Integer comicId, String root, String comicName, String author, Integer status, String type, String location, String newUpdate, String newChapterName, String introduction, Double mark) {
        this.comicId = comicId;
        this.root = root;
        this.comicName = comicName;
        this.author = author;
        this.status = status;
        this.type = type;
        this.location = location;
        this.newUpdate = newUpdate;
        this.newChapterName = newChapterName;
        this.introduction = introduction;
        this.mark = mark;
    }

    public Integer getComicId() {
        return comicId;
    }

    public void setComicId(Integer comicId) {
        this.comicId = comicId;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNewUpdate() {
        return newUpdate;
    }

    public void setNewUpdate(String newUpdate) {
        this.newUpdate = newUpdate;
    }

    public String getNewChapterName() {
        return newChapterName;
    }

    public void setNewChapterName(String newChapterName) {
        this.newChapterName = newChapterName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Comic{" +
                "comicId=" + comicId +
                ", comicName='" + comicName + '\'' +
                ", path='" + root + '\'' +
                ", author='" + author + '\'' +
                ", status=" + status +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", newUpdate='" + newUpdate + '\'' +
                ", newChapterName='" + newChapterName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", mark=" + mark +
                '}';
    }
}
