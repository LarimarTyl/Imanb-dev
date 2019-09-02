package com.larimar.mapper;

import com.larimar.entity.Comment;
import com.larimar.entity.Reply;
import com.larimar.selectPojo.CommentSelect;
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

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/22 周四 10:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml"})
public class CommentMapperTest {
        @Autowired
        private SqlSessionFactory factory;
        @Autowired
        private ApplicationContext context;
        private CommentMapper mapper;

        @Before
        public void init() {
            SqlSession sqlSession = factory.openSession();
            mapper = sqlSession.getMapper(CommentMapper.class);
        }

    @Test
    public void test(){
        System.out.println(mapper.selectByOptions(new CommentSelect(null,0,1)));
    }
    @Test
    public void addComicComment() {
//        System.out.println(mapper.addComicComment(new Comment(12,3,"larimar","larimar",null,2,null,"666",0,"2019-08-21 15:30:21")));
    }

    @Test
    public void addDetailComment() {
//        System.out.println(mapper.addDetailComment(new Comment(10,3,"larimar","larimar",null,null,1,"666",0,"2019-08-21 15:30:21")));
    }

    @Test
    public void addRevertComment() {
//        System.out.println(mapper.addRevertComment(new Comment(11,3,"larimar","larimar",1,null,null,"666",0,"2019-08-21 15:30:21")));
    }

    @Test
    public void delComment() {
        System.out.println(mapper.delComment(11));
    }

    @Test
    public void updateCommentStatus() {
        System.out.println(mapper.updateCommentStatus(12,3,2));
    }

    @Test
    public void selectAllComments() {
        System.out.println(mapper.selectUsersComments(1));
    }

    @Test
    public void selectCommentsByStatus() {
        System.out.println(mapper.selectCommentsByStatus(1,0));
    }

    @Test
    public void selectComicComments() {
        System.out.println(mapper.selectComicComments(1));
    }
    @Test
    public void selectDetailComments() {
        System.out.println(mapper.selectDetailComments(1));
    }
    @Test
    public void selectRevertComments() {
        System.out.println(mapper.selectRevertComments(1));
    }
    @Test
    // TODO: 2019/8/22 测试留言系统
    public void testCommentsSystem(){
        List<Comment> comments = mapper.selectUsersComments(1);

        for (Comment c : comments) {
            Reply reply = new Reply(c);
            Reply comments1 = getComments(reply);
            printlnComment(comments1);
        }
    }



    public Reply getComments(Reply reply){
        Integer comicId = reply.getComicId();
        List<Comment> comments = mapper.selectRevertComments(comicId);
        if (comments!=null){
            List<Reply> replyList = reply.getReplyList();
            for (Comment c : comments) {
                Reply childReply = new Reply(c);
                Reply lastReply = getComments(childReply);
                lastReply.getReplyList().add(lastReply);
                replyList.add(childReply);
            }
            return reply;
        }else {
            return reply;
        }
    }
    public void printlnComment(Reply reply){
        System.out.println("主楼层内容："+reply.getContent());
        List<Reply> replyList = reply.getReplyList();
        for (Reply replys:replyList) {
            printlnComment(replys);
        }
    }
}