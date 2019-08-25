package com.larimar.mapper;

import com.larimar.entity.Orders;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * @author Larimar
 * @time 2019/8/21 周三 15:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml"})
public class OrdersMapperTest {
    @Autowired
    private SqlSessionFactory factory;
    @Autowired
    private ApplicationContext context;
    private OrdersMapper mapper;
    @Before
    public void init(){
        SqlSession sqlSession = factory.openSession();
        mapper = sqlSession.getMapper(OrdersMapper.class);
    }

    @Test
    public void addOrders() {
//        System.out.println(mapper.addOrders(new Orders(7,2,"斗罗大陆",2,"admin",0)));
    }

    @Test
    public void delOrders() {
        System.out.println(mapper.delOrders(7));
    }

    @Test
    public void updateOrders() {
        System.out.println(mapper.updateOrders(1,1));
    }

    @Test
    public void updateUserOrders() {
        System.out.println(mapper.updateUserOrders(2,2,1));
    }

    @Test
    public void selectAllOrders() {
        System.out.println(mapper.selectAllOrders(1));
    }

    @Test
    public void selectAllOrdersByStatus() {
        System.out.println(mapper.selectAllOrdersByStatus(1,0));
    }
}