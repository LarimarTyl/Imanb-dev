package com.larimar.service;

import com.larimar.entity.Orders;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/22 周四 20:40
 */
public interface OrderService {
    public boolean addOrders(Orders orders);
    public boolean delOrders(Integer ordersId);
    public boolean updateOrdersComicStatus(Integer comicId,Integer status);
    public boolean updateUsersComicStatus(Integer comicId,Integer userId,Integer status);
    public List<Orders> selectUsersAllOrders(Integer userId);
    public List<Orders> selectAllOrders();
    public List<Orders> selectAllOrdersByStatus(Integer userId,Integer status);
}
