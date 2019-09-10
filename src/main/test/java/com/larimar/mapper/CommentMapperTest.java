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

import java.util.ArrayList;
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
        List<Comment> comments = mapper.selectComicComments(1);
        Reply comicReply = new Reply();
        List<Reply> replies = new ArrayList<>();
        for (Comment c:comments) {
            Reply reply = new Reply(c);
            replies.add(reply);
        }
        for (Reply r:replies){
           getComments(r);
        }
        comicReply.setReplyList(replies);
        printlnComment(comicReply);
    }



    public void getComments(Reply reply){
            List<Comment> revert = mapper.selectRevertComments(reply.getCommentId());
            //是否有回复
            if (revert.size()>0) {
                List<Reply> revertReplys = new ArrayList<>();
                //把回复内容添加到回复集合
                for (Comment c : revert) {
                    Reply revertReply = new Reply(c);
                    List<Comment> revertComments = mapper.selectRevertComments(revertReply.getCommentId());
                    revertReplys.add(revertReply);
                    //判断子回复是否有回复
                    if (revertComments.size()>0){
                        for (Comment c2 :revertComments){
                            Reply reply2 = new Reply(c2);
                            //把回复的回复也添加到回复列表下
                            revertReplys.add(reply2);
                        }
                    }
                }
                reply.setReplyList(revertReplys);
            }
        }
        //子楼层获取回复



    public void printlnComment(Reply reply){
        List<Reply> replyList = reply.getReplyList();
        for (int i=1;i<replyList.size()+1;i++){
            System.out.println("第"+i+"楼层");
            Reply r = replyList.get(i - 1);
            System.out.println(r);
            //判断是否有回复
            if (r.getReplyList().size()>0){
                List<Reply> childComment = r.getReplyList();
                for (int j=1;j<childComment.size()+1;j++){
                    System.out.println(j+"子楼层");
                    Reply childR = childComment.get(j - 1);
                    System.out.println(childR);
                    System.out.println("--------------");
                }
            }
            System.out.println("================");
        }

    }
}