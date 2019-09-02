package com.larimar.selectPojo;

/**
 * @author Larimar
 * @time 2019/9/2 周一 12:55
 */
public class UserSelect {
    private String userName;
    private String gender;

    public UserSelect() {
    }

    public UserSelect(String userName, String gender) {
        this.userName = userName;
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserSelect{" +
                "userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
