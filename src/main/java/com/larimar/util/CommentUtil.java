package com.larimar.util;

import com.larimar.entity.Comment;
import com.larimar.entity.Reply;
import com.larimar.mapper.CommentMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Larimar
 * @time 2019/9/9 周一 20:19
 */
public class CommentUtil {
    public static CommentMapper mapper;

    public static void getComments(Reply reply){
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
}
