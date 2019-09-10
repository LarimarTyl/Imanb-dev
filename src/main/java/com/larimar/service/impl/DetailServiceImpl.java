package com.larimar.service.impl;

import com.larimar.entity.*;
import com.larimar.mapper.CommentMapper;
import com.larimar.mapper.DetailMapper;
import com.larimar.selectPojo.DetailSelect;
import com.larimar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.larimar.util.CommentUtil.getComments;

/**
 * @author Larimar
 * @time 2019/8/26 周一 12:58
 */
@Service
public class DetailServiceImpl implements DetailService {
    @Autowired
    DetailMapper detailMapper;
    @Autowired
    ComicService comicService;
    @Autowired
    OrderService orderService;
    @Autowired
    HistoryService historyService;
    @Autowired
    CommentMapper commentMapper;

    @Override
    public boolean addDetail(Detail detail) {
        if (detailMapper.addDetail(detail)>0) {
            comicService.updateNewComicDetail(detail.getComicId(),detail.getChapterName(),detail.getUpdateTime());
            orderService.updateOrdersComicStatus(detail.getComicId(),1);
            historyService.updateHistoryStatus(detail.getComicId(),1);
            return true;
        }else return false;
    }

    @Override
    public boolean delDetail(Integer detailId) {
        return detailMapper.delDetail(detailId)>0;
    }

    @Override
    public boolean updateDetail(Detail detail) {
        if (detailMapper.updateDetail(detail)>0) {
            comicService.updateNewComicDetail(detail.getComicId(),detail.getChapterName(),detail.getUpdateTime());
            orderService.updateOrdersComicStatus(detail.getComicId(),1);
            historyService.updateHistoryStatus(detail.getComicId(),1);
            return true;
        }else  return false;
    }

    @Override
    public List<Detail> queryAllDetail() {
        return detailMapper.selectAllDetails();
    }

    @Override
    public List<Detail> selectByOption(DetailSelect detailSelect) {
        return detailMapper.selectByOptions(detailSelect);
    }


    @Override
    public List<Detail> queryComicDetail(Integer comicId) {
        return detailMapper.selectComicAllDetails(comicId);
    }

    @Override
    public List<Detail> selectComicDetailByType(String typeName) {
        return detailMapper.selectDetailsByType(typeName);
    }

    @Override
    public List<Detail> selectComicDetailByTime(String time) {
        return detailMapper.selectDetailsByTime(time);
    }

    @Override
    public List<Detail> queryComicDetailByName(String comicName) {
        return detailMapper.selectDetailsByComicName(comicName);
    }

    @Override
    public Reply getDetailReply(Integer detailId) {
        List<Comment> comments = commentMapper.selectDetailComments(detailId);
        Reply detailReply = new Reply();
        List<Reply> replies = new ArrayList<>();
        for (Comment c:comments) {
            Reply reply = new Reply(c);
            replies.add(reply);
        }
        for (Reply r:replies){
            List<Comment> revert = commentMapper.selectRevertComments(r.getCommentId());
            //是否有回复
            if (revert.size()>0) {
                List<Reply> revertReplys = new ArrayList<>();
                //把回复内容添加到回复集合
                for (Comment c : revert) {
                    Reply revertReply = new Reply(c);
                    List<Comment> revertComments = commentMapper.selectRevertComments(revertReply.getCommentId());
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
                r.setReplyList(revertReplys);
            }
        }
        detailReply.setReplyList(replies);
        return detailReply;
    }

    @Override
    public Detail getDetailById(Integer id) {
        return detailMapper.getDetailById(id);
    }

    @Override
    public Detail selectNewestDetail(Integer comicId) {
        return detailMapper.getNewest(comicId);
    }

    @Override
    public Detail getDetailByComicAndChapter(Integer comicId, String chapterName) {
        return detailMapper.selectDetailByComicIdAndChapter(comicId,chapterName);
    }
}
