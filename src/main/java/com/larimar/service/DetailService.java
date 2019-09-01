package com.larimar.service;

import com.larimar.entity.Detail;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/22 周四 20:41
 */
public interface DetailService {
    public boolean addDetail(Detail detail);
    public boolean delDetail(Integer detailId);
    public boolean updateDetail(Detail detail);
    public Detail getDetailById(Integer id);
    public Detail getDetailByComicAndChapter(Integer comicId,String chapterName);
    public List<Detail> queryAllDetail();
    public List<Detail> queryComicDetail(Integer comicId);
    public List<Detail> selectComicDetailByType(String typeName);
}
