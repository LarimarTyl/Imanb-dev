package com.larimar.service.impl;

import com.larimar.entity.Comic;
import com.larimar.entity.Conditon;
import com.larimar.mapper.ComicMapper;
import com.larimar.selectPojo.ComicSelect;
import com.larimar.selectPojo.CommentSelect;
import com.larimar.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/23 周五 15:41
 */
@Service
public class ComicServiceImpl implements ComicService {
    @Autowired
    ComicMapper comicMapper;
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
}
