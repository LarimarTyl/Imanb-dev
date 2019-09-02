package com.larimar.selectPojo;

/**
 * @author Larimar
 * @time 2019/9/2 周一 12:55
 */
public class ComicSelect {
    private String comicName;
    private String typeName;
    private String location;
    private Integer status;

    public ComicSelect() {
    }

    public ComicSelect(String comicName, String typeName, String location,Integer status) {
        this.comicName = comicName;
        this.typeName = typeName;
        this.location = location;
        this.status = status;
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ComicSelect{" +
                "comicName='" + comicName + '\'' +
                ", typeName='" + typeName + '\'' +
                ", location='" + location + '\'' +
                ", status=" + status +
                '}';
    }
}
