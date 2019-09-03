package com.larimar.service;

/**
 * @author Larimar
 * @time 2019/9/2 周一 23:04
 */
public interface LikeService {
    public boolean addComicLikes(Integer comicId);
    public boolean addDetailLikes(Integer detailId);
    public boolean delComicLikes(Integer comicId);
    public boolean delDetailLikes (Integer detailId);
    public boolean updateDetailLikes(Integer detailId,Integer number);
    public boolean updateComicLikes(Integer comicId,Integer number);
    public int selectComicLikeNum(Integer comicId);
    public int selectDetailLikeNum(Integer detailId);
}
