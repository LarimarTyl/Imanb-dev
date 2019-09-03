package com.larimar.mapper;

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
 * @time 2019/8/21 周三 18:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml"})
public class LikesMapperTest {
    @Autowired
    private SqlSessionFactory factory;
    @Autowired
    private ApplicationContext context;
    private LikesMapper mapper;
    @Before
    public void init(){
        SqlSession sqlSession = factory.openSession();
        mapper = sqlSession.getMapper(LikesMapper.class);
    }

    @Test
    public void addComicLikes() {
        System.out.println(mapper.selectComicLikes(8));
    }

    @Test
    public void addDetailLikes() {
        System.out.println(mapper.addDetailLikes(3));
    }

    @Test
    public void delDetailLikes() {
        System.out.println(mapper.delDetailLikes(3));
    }

    @Test
    public void delComicLikes() {
        System.out.println(mapper.delComicLikes(4));
    }

    @Test
    public void updateComicLikes() {
        System.out.println(mapper.updateComicLikes(1,100));
    }

    @Test
    public void updateDetailLikes() {
        System.out.println(mapper.queryAllLikes());
    }

    @Test
    public void selectDetailLikes() {
        System.out.println(mapper.selectDetailLikes(1));
    }

    @Test
    public void selectComicLikes() {
        System.out.println(mapper.selectComicLikes(1));
    }
}