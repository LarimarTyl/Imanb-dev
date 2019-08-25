package com.larimar.mapper;

import com.larimar.entity.User;
import org.apache.ibatis.annotations.Param;

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
     * @param userId 用户id
     * @return 删除用户数
     */
    public int delUser(Integer userId);

    /**
     * 根据用户名删除用户
     * @param userName 用户名
     * @return 删除用户数
     */
    public int delUserByName(String userName);

    /**
     * 更改用户信息
     * @param user 新的用户对象
     * @return 更新用户数
     */
    public int updateUser(User user);

    /**
     * 更改用户注册状态
     * @param userId 用户id
     * @param status 用户状态
     * @return 修改数量
     */
    public int updateUserStatus(@Param("userId") Integer userId ,@Param("status")Integer status);

    /**
     * 更改用户密码
     * @param newPassWord 新的密码
     * @param userId 用户id
     * @return 更改用户数
     */
    public int updateUserPassWord(@Param("newPassWord")String newPassWord ,@Param("userId") Integer userId);

    /**
     * 更改用户头像
     * @param newPhoto 新头像路径名（文件名）
     * @param userId 用户id
     * @return 更改用户数
     */
    public int updateUserPhoto(@Param("newPhoto") String newPhoto,@Param("userId")Integer userId);

    /**
     * 查询用户名是否存在（根据用户名查找用户）
     * @param userName 用户名
     * @return 对应的用户
     */
    public User findUserByName(String userName);

    /**
     * 根据用户id查找用户
     * @param userId 用户id
     * @return 用户
     */
    public  User findUserById(Integer userId);

    /**
     * 查询用户的注册状态
     * @param userName 用户名
     * @return 注册状态码
     */
    public int findUserStatus(String userName);

    /**
     * 根据用户名和密码查找用户（登录）
     * @param userName 用户名
     * @param password 密码
     * @return 对应的用户
     */
    public User findUserByUserNameAndPsw(@Param("userName") String userName,@Param("password")String password);

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
    public  List<User> selectUserNameLike(@Param("name") String name);
}
