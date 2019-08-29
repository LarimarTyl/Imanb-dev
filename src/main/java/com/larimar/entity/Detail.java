package com.larimar.entity;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/20 周二 17:52
 */
public class Detail {
    private Integer detailId;//详情页id
    private Integer comicId;//漫画id
    private Comic comic;//漫画对象
    private String chapterName;//章节名
    private String path;//章节图片地址
    private String generalize;//章节概况
    private String updateTime;//章节更新时间
    private List<String> images;//指定文件名

    public Detail() {
    }

    public Detail(Integer detailId, Integer comicId, Comic comic, String chapterName, String path, String introduction, String updateTime) {
        this.detailId = detailId;
        this.comicId = comicId;
        this.comic = comic;
        this.chapterName = chapterName;
        this.path = path;
        this.generalize = introduction;
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

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
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

    public String getGeneralize() {
        return generalize;
    }

    public void setGeneralize(String generalize) {
        this.generalize = generalize;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "detailId=" + detailId +
                ", comicId=" + comicId +
                ", comic=" + comic +
                ", chapterName='" + chapterName + '\'' +
                ", path='" + path + '\'' +
                ", generalize='" + generalize + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
