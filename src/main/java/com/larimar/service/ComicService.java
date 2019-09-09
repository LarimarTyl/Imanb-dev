package com.larimar.service;

import com.larimar.entity.Comic;
import com.larimar.entity.Conditon;
import com.larimar.selectPojo.ComicSelect;
import com.larimar.selectPojo.CommentSelect;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/22 周四 20:38
 */
public interface ComicService {
    public boolean addComic(Comic comic);
    public boolean delComic(Integer comicId);
    public boolean updateComicInfo(Comic comic);
    public boolean updateComicStatus(Integer comicId,Integer status);
    public boolean updateNewComicDetail(Integer comicId,String comicName,String newUpdateTime);
    public Comic getComicById(Integer comicId);
    public Comic getComicByName(String comicName);
    public List<Comic> queryComicTypeLike(String type);
    public List<Comic> queryComicLocation(String location);
    public List<Comic> queryAllComic();
    public List<Comic> selectByOption(ComicSelect comicSelect);
    public List<Comic> findLikestComic();
    public List<Comic> findNewComic();
    public List<Comic> findComicByOrderNum();
    public List<Comic> findComicByStatus(Integer status);
    public List<Comic> findUserOrderComic(Integer userId);
    public List<Comic> findComicByCondition(Conditon condition);
    public List<Comic> findChineseRank();
    public List<Comic> findJapaneseRank();
    public List<Comic> findAllRank();

    public List<Comic> getComicNameLike(String comicName);
}
