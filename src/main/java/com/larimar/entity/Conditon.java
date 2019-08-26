package com.larimar.entity;

/**
 * @author Larimar
 * @time 2019/8/26 周一 13:28
 */

/**
 * 查询条件
 */
public class Conditon {
    private String ComicName;
    private String Author;
    private String typeName;
    private String Location;
    private Integer status;

    public Conditon(String comicName, String author, String typeName, String location, Integer status) {
        ComicName = comicName;
        Author = author;
        this.typeName = typeName;
        Location = location;
        this.status = status;
    }

    public String getComicName() {
        return ComicName;
    }

    public void setComicName(String comicName) {
        ComicName = comicName;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
