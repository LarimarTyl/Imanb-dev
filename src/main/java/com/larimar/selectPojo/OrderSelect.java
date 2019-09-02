package com.larimar.selectPojo;

/**
 * @author Larimar
 * @time 2019/9/2 周一 12:56
 */
public class OrderSelect {
    private String comicName;
    private String userName;
    private String typeName;
    private String Location;

    public OrderSelect() {
    }

    public OrderSelect(String comicName, String userName, String typeName, String location) {
        this.comicName = comicName;
        this.userName = userName;
        this.typeName = typeName;
        Location = location;
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public String toString() {
        return "OrderSelect{" +
                "comicName='" + comicName + '\'' +
                ", userName='" + userName + '\'' +
                ", typeName='" + typeName + '\'' +
                ", Location='" + Location + '\'' +
                '}';
    }
}
