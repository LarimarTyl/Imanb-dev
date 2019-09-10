package com.larimar.mapper;

import com.larimar.entity.Comic;
import com.larimar.entity.Detail;
import com.larimar.selectPojo.DetailSelect;
import com.larimar.util.DateUtil;
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
 * @time 2019/8/21 周三 20:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml"})
public class DetailMapperTest {
    @Autowired
    private SqlSessionFactory factory;
    @Autowired
    private ApplicationContext context;
    private DetailMapper mapper;

    @Before
    public void init() {
        SqlSession sqlSession = factory.openSession();
        mapper = sqlSession.getMapper(DetailMapper.class);
    }

    @Test
    public void addDetail() {
        for (int i = 7; i < 65; i++) {
            Detail detail = new Detail();
            detail.setComicId(2);
            int index = i + 1;
            detail.setChapterName("第"+index+"章");
            detail.setPath(String.valueOf(index));
            detail.setGeneralize("第"+index+"章");
            detail.setUpdateTime(DateUtil.localDateToString());
            mapper.addDetail(detail);
        }
       }

    @Test
    public void delDetail() {
        System.out.println(mapper.delDetail(24));
    }

    @Test
    public void updateDetailUpdateTime() {
        System.out.println(mapper.updateDetail(new Detail(2,1,new Comic(1,null,"斗罗大陆",null,null,null,null,null,null,null,null),"唐三穿越（中）","2","双生武魂","2019-08-20 14:49:05")));
    }

    @Test
    public void selectAllDetails() {
        System.out.println(mapper.selectAllDetails());
    }

    @Test
    public void selectDetailsByType() {
        System.out.println(mapper.selectDetailsByType("冒险"));
    }
}