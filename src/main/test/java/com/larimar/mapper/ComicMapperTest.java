package com.larimar.mapper;

import com.larimar.entity.Comic;
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
 * @time 2019/8/22 周四 16:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml"})
public class ComicMapperTest {
        @Autowired
        private SqlSessionFactory factory;
        @Autowired
        private ApplicationContext context;
        private ComicMapper mapper;

        @Before
        public void init() {
            SqlSession sqlSession = factory.openSession();
            mapper = sqlSession.getMapper(ComicMapper.class);
        }
        @Test
        public void test(){
            System.out.println(mapper.selectComicsByLikes());
            System.out.println("=============");
            System.out.println(mapper.selectNewComics());
            System.out.println("=============");
            System.out.println(mapper.selectComicByOrder());
            System.out.println("=============");
            System.out.println(mapper.selectChineseComicRank());
            System.out.println("=============");
            System.out.println(mapper.selectJapaneseRank());
            System.out.println("=============");
            System.out.println(mapper.selectAllRank());
        }
    @Test
    public void addComic() {
        Comic comic = new Comic(5,"jstm","绝世唐门","唐家三少",0,"玄幻=热血=冒险","国漫","2019-08-21 14:44:36","第一话:百万年魂环","百万年魂环，伊莱克斯？？？",4.5);
        System.out.println(mapper.addComic(comic));
    }

    @Test
    public void delComic() {
        System.out.println(mapper.delComic(5));
    }

    @Test
    public void updateComic() {
        Comic comic = new Comic(5,"jstm","绝世唐门up","唐家三少",0,"玄幻=热血=冒险","国漫","2019-08-21 14:44:36","第一话:百万年魂环","百万年魂环，伊莱克斯？？？",4.5);
        System.out.println(mapper.updateComic(comic));
    }

    @Test
    public void updateComicStatus() {
        System.out.println(mapper.updateComicStatus(5,-1));
    }

    @Test
    public void updateComicNewsUpdateTime() {
        System.out.println(mapper.updateComicNewsUpdateTime(5,"2019-08-21 14:45:36","第二话:伊莱克斯哦"));
    }

    @Test
    public void selectComicById() {
        System.out.println(mapper.selectComicById(3));
    }

    @Test
    public void selectComicByName() {
        System.out.println(mapper.selectComicByName("斗罗大陆"));
    }

    @Test
    public void selectComicByTypeName() {
        System.out.println(mapper.selectComicByTypeName("玄幻"));
    }

    @Test
    public void selectComicByAuthor() {
        System.out.println(mapper.selectComicByAuthor("唐家三少"));
    }

    @Test
    public void selectComicByStatus() {
        System.out.println(mapper.selectComicByStatus(1));
    }

    @Test
    public void selectComicByLocation() {
        System.out.println(mapper.selectComicByLocation("日漫"));
    }

    @Test
    public void selectComicByOrder() {
        System.out.println(mapper.selectComicByOrder());
    }

    @Test
    public void selectNewComics() {
        System.out.println(mapper.selectNewComics());
    }

    @Test
    public void selectComicsByLikes() {
        System.out.println(mapper.selectComicsByLikes());
    }

    @Test
    public void selectOrderComic() {
        System.out.println(mapper.selectOrderComic(1));
    }

    @Test
    public void selectAllComics() {
        System.out.println(mapper.selectAllComics());
    }

}