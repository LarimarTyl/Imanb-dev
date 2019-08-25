package com.larimar.service;

import com.larimar.entity.User;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/22 周四 20:37
 */
public interface UserService {
    public User doLogin(String userName,String passWord);
    public boolean doRegister(User user);
    public boolean isExit(String userName);
    public boolean isActive(String userName);
    public boolean Active(Integer userId);
    public boolean delUser(Integer userId);
    public boolean delUserByName(String userName);
    public boolean updateUserInfo(User user);
    public boolean updatePassWord(String newPassWord,Integer userId);
    public boolean updatePhoto(String newPhoto,Integer userId);
    public List<User> queryAllUsers();
    public User findUserById(Integer userId);
    public User findUserByName(String userName);
}
