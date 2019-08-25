package com.larimar.service.impl;

import com.larimar.entity.User;
import com.larimar.mapper.UserMapper;
import com.larimar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/22 周四 20:49
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User doLogin(String userName, String passWord) {
       return userMapper.findUserByUserNameAndPsw(userName,passWord);
    }

    @Override
    public boolean doRegister(User user) {
       return userMapper.saveUser(user)>0;

    }

    @Override
    public boolean isExit(String userName) {
        return userMapper.selectUserNameLike(userName)!=null;
    }

    @Override
    public boolean isActive(String userName) {
        return userMapper.findUserStatus(userName)>0;
    }

    @Override
    public boolean Active(Integer userId) {
        return userMapper.updateUserStatus(userId,1)>0;
    }

    @Override
    public boolean delUser(Integer userId) {
        return userMapper.delUser(userId)>0;
    }

    @Override
    public boolean delUserByName(String userName) {
        return userMapper.delUserByName(userName)>0;
    }

    @Override
    public boolean updateUserInfo(User user) {
        return userMapper.updateUser(user)>0;
    }

    @Override
    public boolean updatePassWord(String newPassWord, Integer userId) {
        return userMapper.updateUserPassWord(newPassWord,userId)>0;
    }

    @Override
    public boolean updatePhoto(String newPhoto, Integer userId) {
        return userMapper.updateUserPhoto(newPhoto,userId)>0;
    }

    @Override
    public List<User> queryAllUsers() {
        return userMapper.selectAllUsers();
    }

    @Override
    public User findUserById(Integer userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public User findUserByName(String userName) {
        return userMapper.findUserByName(userName);
    }

}
