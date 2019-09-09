package com.larimar.service;

import com.larimar.entity.Detail;
import com.larimar.selectPojo.DetailSelect;

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
    public Detail selectNewestDetail(Integer comicId);
    public Detail getDetailByComicAndChapter(Integer comicId,String chapterName);
    public List<Detail> queryAllDetail();
    public List<Detail> selectByOption(DetailSelect detailSelect);
    public List<Detail> queryComicDetail(Integer comicId);
    public List<Detail> selectComicDetailByType(String typeName);
    public List<Detail> selectComicDetailByTime(String time);

    public List<Detail> queryComicDetailByName(String comicName);
}
