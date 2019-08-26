package com.larimar.service.impl;

import com.larimar.entity.Orders;
import com.larimar.mapper.OrdersMapper;
import com.larimar.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/26 周一 12:59
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrdersMapper ordersMapper;
    @Override
    public boolean addOrders(Orders orders) {
        return ordersMapper.addOrders(orders)>0;
    }

    @Override
    public boolean delOrders(Integer ordersId) {
        return ordersMapper.delOrders(ordersId)>0;
    }

    @Override
    public boolean updateOrdersComicStatus(Integer comicId, Integer status) {
        return ordersMapper.updateOrders(comicId,status)>0;
    }

    @Override
    public boolean updateUsersComicStatus(Integer comicId, Integer userId, Integer status) {
        return ordersMapper.updateUserOrders(comicId, userId, status)>0;
    }

    @Override
    public List<Orders> selectUsersAllOrders(Integer userId) {
        return ordersMapper.selectUsersAllOrders(userId);
    }

    @Override
    public List<Orders> selectAllOrders() {
        return ordersMapper.selectAllOrders();
    }

    @Override
    public List<Orders> selectAllOrdersByStatus(Integer userId, Integer status) {
        return ordersMapper.selectAllOrdersByStatus(userId, status);
    }

}
