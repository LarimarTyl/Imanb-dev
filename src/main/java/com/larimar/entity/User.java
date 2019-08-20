package com.larimar.entity;

/**
 * @author Larimar
 * @time 2019/8/20 周二 17:42
 */
public class User {
    private Integer userId; //用户id
    private String userName;//用户名
    private String password;//密码
    private String gender;//性别
    private String email;//邮箱
    private String qq;//QQ号码
    private String nickName;//昵称
    private String photo;//头像文件名
    private Integer status;//状态 0未激活 1已激活

    public User() {
    }

    public User(Integer userId, String userName, String password, String gender, String email, String qq, String nickName, String photo, Integer status) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.qq = qq;
        this.nickName = nickName;
        this.photo = photo;
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", nickName='" + nickName + '\'' +
                ", photo='" + photo + '\'' +
                ", status=" + status +
                '}';
    }
}
