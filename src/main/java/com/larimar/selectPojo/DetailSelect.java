package com.larimar.selectPojo;

/**
 * @author Larimar
 * @time 2019/9/2 周一 12:56
 */
public class DetailSelect {
    private String comicName;
    private String typeName;
    private String location;

    public DetailSelect() {
    }

    public DetailSelect(String comicName, String typeName, String location) {
        this.comicName = comicName;
        this.typeName = typeName;
        this.location = location;
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

    @Override
    public String toString() {
        return "DetailSelect{" +
                "comicName='" + comicName + '\'' +
                ", typeName='" + typeName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
