package com.larimar.service;

import com.larimar.entity.Orders;
import com.larimar.selectPojo.OrderSelect;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/22 周四 20:40
 */
public interface OrderService {
    public boolean addOrders(Orders orders);
    public boolean delOrders(Integer ordersId);
    public boolean updateOrdersComicStatus(Integer comicId,Integer status);
    public boolean updateOrdersInfo(Orders orders);
    public boolean updateUsersComicStatus(Integer comicId,Integer userId,Integer status);
    public Orders getOrdersById(Integer ordersId);
    public List<Orders> selectUsersAllOrders(Integer userId);
    public List<Orders> selectAllOrders();
    public List<Orders> selectByOption(OrderSelect orderSelect);
    public List<Orders> selectAllOrdersByStatus(Integer userId,Integer status);
}
