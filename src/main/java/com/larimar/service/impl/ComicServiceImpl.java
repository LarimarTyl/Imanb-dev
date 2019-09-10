package com.larimar.service.impl;

import com.larimar.entity.Comic;
import com.larimar.entity.Comment;
import com.larimar.entity.Conditon;
import com.larimar.entity.Reply;
import com.larimar.mapper.ComicMapper;
import com.larimar.mapper.CommentMapper;
import com.larimar.selectPojo.ComicSelect;
import com.larimar.selectPojo.CommentSelect;
import com.larimar.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.larimar.util.CommentUtil.getComments;

/**
 * @author Larimar
 * @time 2019/8/23 周五 15:41
 */
@Service
public class ComicServiceImpl implements ComicService {
    @Autowired
    ComicMapper comicMapper;
    @Autowired
    CommentMapper commentMapper;
    @Override
    public boolean addComic(Comic comic) {
        return comicMapper.addComic(comic)>0;
    }

    @Override
    public boolean delComic(Integer comicId) {
        return comicMapper.delComic(comicId)>0;
    }

    @Override
    public boolean updateComicInfo(Comic comic) {
        return comicMapper.updateComic(comic)>0;
    }

    @Override
    public boolean updateComicStatus(Integer comicId, Integer status) {
        return comicMapper.updateComicStatus(comicId,status)>0;
    }

    @Override
    public boolean updateNewComicDetail(Integer comicId, String userName, String newUpdateTime) {
        return comicMapper.updateComicNewsUpdateTime(comicId,userName,newUpdateTime)>0;
    }

    @Override
    public Comic getComicById(Integer comicId) {
        return comicMapper.selectComicById(comicId);
    }

    @Override
    public Comic getComicByName(String comicName) {
        return comicMapper.selectComicByName(comicName);
    }

    @Override
    public List<Comic> queryComicTypeLike(String type) {
        return comicMapper.selectComicByTypeName(type);
    }

    @Override
    public List<Comic> queryComicLocation(String location) {
        return comicMapper.selectComicByLocation(location);
    }

    @Override
    public List<Comic> queryAllComic() {
        return comicMapper.selectAllComics();
    }

    @Override
    public List<Comic> selectByOption(ComicSelect comicSelect) {
        return comicMapper.selectByOptions(comicSelect);
    }

    @Override
    public List<Comic> findLikestComic() {
        return comicMapper.selectComicsByLikes();
    }

    @Override
    public List<Comic> findNewComic() {
        return comicMapper.selectNewComics();
    }

    @Override
    public List<Comic> findComicByOrderNum() {
        return comicMapper.selectComicByOrder();
    }

    @Override
    public List<Comic> findComicByStatus(Integer status) {
        return comicMapper.selectComicByStatus(status);
    }

    @Override
    public List<Comic> findUserOrderComic(Integer userId) {
        return comicMapper.selectOrderComic(userId);
    }

    @Override
    public List<Comic> findComicByCondition(Conditon condition) {
        return null;
    }

    @Override
    public List<Comic> findChineseRank() {
        return comicMapper.selectChineseComicRank();
    }

    @Override
    public List<Comic> findJapaneseRank() {
        return comicMapper.selectJapaneseRank();
    }

    @Override
    public List<Comic> findAllRank() {
        return comicMapper.selectAllRank();
    }

    @Override
    public Reply getComicReply(Integer comicId) {
        List<Comment> comments = commentMapper.selectComicComments(comicId);
        Reply comicReply = new Reply();
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
        comicReply.setReplyList(replies);
        return comicReply;
    }

    @Override
    public List<Comic> getComicNameLike(String comicName) {
        return comicMapper.selectComicLikeName(comicName);
    }
}
