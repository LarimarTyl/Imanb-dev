package com.larimar.entity;

/**
 * @author Larimar
 * @time 2019/8/20 周二 17:52
 */
public class Detail {
    private Integer detailId;//详情页id
    private Integer comicId;//漫画id
    private String comicName;//漫画名
    private String chapterName;//章节名
    private String path;//章节图片地址
    private String introduction;//章节简介
    private String updateTime;//章节更新时间

    public Detail() {
    }

    public Detail(Integer detailId, Integer comicId, String comicName, String chapterName, String path, String introduction, String updateTime) {
        this.detailId = detailId;
        this.comicId = comicId;
        this.comicName = comicName;
        this.chapterName = chapterName;
        this.path = path;
        this.introduction = introduction;
        this.updateTime = updateTime;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
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

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "detailId=" + detailId +
                ", comicId=" + comicId +
                ", comicName='" + comicName + '\'' +
                ", chapterName='" + chapterName + '\'' +
                ", path='" + path + '\'' +
                ", introduction='" + introduction + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
