package com.larimar.mapper;

import com.larimar.entity.User;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/20 周二 19:27
 */
public interface UserMapper {
    /**
     * 添加用户
     * @param user 用户对象
     * @return 新增用户数
     */
    public int saveUser(User user);

    /**
     * 删除用户
     * @param id 用户id
     * @return 删除用户数
     */
    public int delUser(Integer id);

    /**
     * 根据用户名删除用户
     * @param userName 用户名
     * @return 删除用户数
     */
    public int delUserByName(Integer userName);

    /**
     * 更改用户信息
     * @param user 新的用户对象
     * @return 更新用户数
     */
    public int updateUser(User user);

    /**
     * 更改用户注册状态
     * @param UserId
     * @return
     */
    public int updateUserStatus(Integer UserId);

    /**
     * 更改用户密码
     * @param newPassWord 新的密码
     * @param userId 用户id
     * @return 更改用户数
     */
    public int updateUserPassWord(String newPassWord ,Integer userId);

    /**
     * 更改用户头像
     * @param newPhoto 新头像路径名（文件名）
     * @param userId 用户id
     * @return 更改用户数
     */
    public int updateUserPhoto(String newPhoto,Integer userId);

    /**
     * 查询用户名是否存在（根据用户名查找用户）
     * @param userName 用户名
     * @return 对应的用户
     */
    public User findUserName(String userName);

    /**
     * 根据用户id查找用户
     * @param userId 用户id
     * @return 用户
     */
    public  User findUserById(Integer userId);

    /**
     * 查询用户的注册状态
     * @param userId 用户id
     * @return 注册状态码
     */
    public int findUserStatus(Integer userId);

    /**
     * 根据用户名和密码查找用户（登录）
     * @param userName 用户名
     * @param password 密码
     * @return 对应的用户
     */
    public User findUserByUserNameAndPsw(String userName,String password);

    /**
     * 查找所有用户
     * @return 所用用户集合
     */
    public List<User> selectAllUsers();

    /**
     * 根据用户名或昵称粗查找用户（搜索栏用）
     * @param name 用户名/昵称
     * @return 用户集合
     */
    public  List<User> selectUserNameLike(String name);
}
