package com.larimar.mapper;

import com.larimar.entity.User;
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

/**
 * @author Larimar
 * @time 2019/8/21 周三 9:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml"})
public class UserMapperTest {
    @Autowired
    private SqlSessionFactory factory;
    @Autowired
    private ApplicationContext context;
    private UserMapper mapper;
    @Before
    public void init(){
        SqlSession sqlSession = factory.openSession();
         mapper = sqlSession.getMapper(UserMapper.class);
    }

//    @Test
//    public void saveUser() {
//        System.out.println(mapper.saveUser(new User(99,"test","女","yue","875843160@qq.com","875843160","que","user",0)));
//        System.out.println(mapper.saveUser(new User(100,"test","女","yue","875843160@qq.com","875843160","que","user",0)));
//    }

//    @Test
//    public void delUser() {
//        System.out.println(mapper.delUser(99));
//    }

//    @Test
//    public void delUserByName() {
//        System.out.println(mapper.delUserByName("test"));
//    }

    @Test
    public void updateUser() {
        System.out.println(mapper.updateUser(new User(4,"yue","女","yue","875843160@qq.com","875843160","que","user",0)));
    }

    @Test
    public void updateUserStatus() {
        System.out.println(mapper.updateUserStatus(4,1));
    }

    @Test
    public void updateUserPassWord() {
        System.out.println(mapper.updateUserPassWord("tyl123",3));
    }

    @Test
    public void updateUserPhoto() {
        System.out.println(mapper.updateUserPhoto("larimar",3));
    }

    @Test
    public void testFindUserByName() {
        System.out.println(mapper.findUserByName("larimar"));
    }

    @Test
    public void testFindUserById() {
        System.out.println(mapper.findUserById(1));
    }

    @Test
    public void testFindUserStatus() {
        System.out.println(mapper.findUserStatus(2));
    }

    @Test
    public void testFindUserByUserNameAndPsw() {
        System.out.println(mapper.findUserByUserNameAndPsw("root","root"));
    }

    @Test
    public void testSelectAllUsers() {
        System.out.println(mapper.selectAllUsers());
    }

    @Test
    public void testSelectUserNameLike() {
        System.out.println(mapper.selectUserNameLike("a"));
    }
}
