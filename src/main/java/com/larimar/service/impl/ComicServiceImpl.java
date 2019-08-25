package com.larimar.service.impl;

import com.larimar.entity.Comic;
import com.larimar.service.ComicService;
import org.springframework.stereotype.Service;

/**
 * @author Larimar
 * @time 2019/8/23 周五 15:41
 */
@Service
public class ComicServiceImpl implements ComicService {
    @Override
    public boolean addComic(Comic comic) {
        return false;
    }

    @Override
    public boolean delComic(Integer comicId) {
        return false;
    }

    @Override
    public boolean updateComicInfo(Comic comic) {
        return false;
    }

    @Override
    public boolean updateComicStatus(Integer comicId, Integer status) {
        return false;
    }

    @Override
    public boolean updateNewComicDetail(Integer comicId, String userName, String newUpdateTime, Integer status) {
        return false;
    }
}
