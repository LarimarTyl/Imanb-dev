package com.larimar.selectPojo;

/**
 * @author Larimar
 * @time 2019/9/2 周一 12:57
 */
public class HistorySelect {
    private String userName;
    private String comicName;
    private Integer status;

    public HistorySelect() {
    }

    public HistorySelect(String userName, String comicName, Integer status) {
        this.userName = userName;
        this.comicName = comicName;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HistorySelect{" +
                "userName='" + userName + '\'' +
                ", comicName='" + comicName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
