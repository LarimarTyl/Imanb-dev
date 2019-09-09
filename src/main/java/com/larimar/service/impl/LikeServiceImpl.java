package com.larimar.service.impl;

import com.larimar.mapper.LikesMapper;
import com.larimar.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Larimar
 * @time 2019/9/2 周一 23:14
 */
@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikesMapper likesMapper;

    @Override
    public boolean addComicLikes(Integer comicId) {
        int i  = selectComicLikeNum(comicId);
        if (i>0){
            return updateComicLikes(comicId,i+1);
        }else {
            return likesMapper.addComicLikes(comicId)>0;
        }
    }

    @Override
    public boolean addDetailLikes(Integer detailId) {
        int i  = selectDetailLikeNum(detailId);
        if (i>0){
            return updateDetailLikes(detailId,i+1);
        }else {
            return likesMapper.addDetailLikes(detailId)>0;
        }
    }

    @Override
    public boolean delComicLikes(Integer comicId) {
        int i = selectComicLikeNum(comicId);
        if (i>1){
            return updateComicLikes(comicId,i-1);
        }else{
            return likesMapper.delComicLikes(comicId)>0;
        }
    }

    @Override
    public boolean delDetailLikes(Integer detailId) {
        int i = selectDetailLikeNum(detailId);
        if (i>1){
            return updateDetailLikes(detailId,i-1);
        }else{
            return likesMapper.delDetailLikes(detailId)>0;
        }
    }

    @Override
    public boolean updateDetailLikes(Integer detailId, Integer number) {
        return likesMapper.updateDetailLikes(detailId,number)>0;
    }

    @Override
    public boolean updateComicLikes(Integer comicId, Integer number) {
        return likesMapper.updateComicLikes(comicId,number)>0;
    }

    @Override
    public int selectComicLikeNum(Integer comicId){
        int i = 0;
        try {
            i=likesMapper.selectComicLikes(comicId);
        } catch (Exception e) {
            return 0;
        }

        return i;
    }

    @Override
    public int selectDetailLikeNum(Integer detailId) {
        int i = 0;
        try {
//            return likesMapper.selectDetailLikes(detailId).orElse(0);
            i=likesMapper.selectDetailLikes(detailId);
        } catch (Exception e) {
            return 0;
        }
        return i;
    }
}
