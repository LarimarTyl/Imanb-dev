package com.larimar.mapper;

import com.larimar.entity.Types;
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
 * @time 2019/8/21 周三 17:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml"})
public class TypeMapperTest {
    @Autowired
    private SqlSessionFactory factory;
    @Autowired
    private ApplicationContext context;
    private TypeMapper mapper;
    @Before
    public void init(){
        SqlSession sqlSession = factory.openSession();
        mapper = sqlSession.getMapper(TypeMapper.class);
    }

    @Test
    public void addType() {
        System.out.println(mapper.addType(new Types(13,"恐怖")));
    }

    @Test
    public void delType() {
        System.out.println(mapper.delType("恐怖"));
    }

    @Test
    public void selectAllTypes() {
        System.out.println(mapper.selectAllTypes());
    }
}