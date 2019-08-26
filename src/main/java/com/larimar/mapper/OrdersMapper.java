package com.larimar.mapper;

import com.larimar.entity.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/20 周二 19:31
 */
public interface OrdersMapper {
    /**
     * 添加订阅信息
     * @param orders 订阅对象
     * @return 订阅数
     */
    public int addOrders(Orders orders);

    /**
     * 删除订阅信息
     * @param ordersId 订阅表id
     * @return删除数
     */
    public int delOrders(Integer ordersId);

    /**
     * 更改订阅漫画状态（是否更新）
     * @param comicId 漫画id
     * @param status 漫画状态
     * @return 更新数量
     */
    public int updateOrders(@Param("comicId") Integer comicId,@Param("status") Integer status);

    /**
     * 更改用户订阅状态（是否已读）
     * @param comicId 漫画id
     * @param status 漫画状态
     * @param userId 用户id
     * @return 更新数量
     */
    public int updateUserOrders(@Param("comicId") Integer comicId,@Param("userId")Integer userId,@Param("status")Integer status);

    /**
     * 查询用户的所有订阅
     * @param userId 用户id
     * @return 订阅集合
     */
    public List<Orders> selectUsersAllOrders(Integer userId);
    /**
     * 查询所有订阅信息
     * @return 订阅集合
     */
    public List<Orders> selectAllOrders();
    /**
     * 查询用户指定状态的订阅（未读更新订阅 已读更新订阅）
     * @param userId 用户id
     * @param status 状态码（0未更新； 1已更新； -1未读更新）
     * @return
     */
    public List<Orders> selectAllOrdersByStatus(@Param("userId") Integer userId,@Param("status") Integer status);
}
