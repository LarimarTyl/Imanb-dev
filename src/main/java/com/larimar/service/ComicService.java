package com.larimar.service;

import com.larimar.entity.Comic;

/**
 * @author Larimar
 * @time 2019/8/22 周四 20:38
 */
public interface ComicService {
    public boolean addComic(Comic comic);
    public boolean delComic(Integer comicId);
    public boolean updateComicInfo(Comic comic);
    public boolean updateComicStatus(Integer comicId,Integer status);
    public boolean updateNewComicDetail(Integer comicId,String userName,String newUpdateTime,Integer status);
}
