package com.larimar.mapper;

import com.larimar.entity.History;
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
 * @time 2019/8/21 周三 19:35
 */
    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:applicationContext-dao.xml"})
public class HistoryMapperTest {
        @Autowired
        private SqlSessionFactory factory;
        @Autowired
        private ApplicationContext context;
        private HistoryMapper mapper;
        @Before
        public void init(){
            SqlSession sqlSession = factory.openSession();
            mapper = sqlSession.getMapper(HistoryMapper.class);
        }
    @Test
    public void test() {
        System.out.println(mapper.selectHistoryById(3));
    }
    @Test
    public void addHistory() {
        System.out.println(mapper.addHistory(new History(3,1,null,3,null,17,null,"2019-08-21 14:49:06",0)));
    }

    @Test
    public void delHistory() {
        System.out.println(mapper.delHistory(3));
    }

    @Test
    public void updateHistory() {
        System.out.println(mapper.updateHistoryStatus(1,0));
    }

    @Test
    public void updateHistoryStatus() {
//        System.out.println(mapper.updateHistory(new History(1,1,"root",1,"海贼王",4,"第4话：大师？老师？（上）","2019-08-21 11:49:06",-1)));
    }

    @Test
    public void selectAllHistory() {
        System.out.println(mapper.selectAllHistory());
    }

    @Test
    public void selectHistoryByStatus() {
        System.out.println(mapper.selectHistoryByStatus(1,1));
    }
}