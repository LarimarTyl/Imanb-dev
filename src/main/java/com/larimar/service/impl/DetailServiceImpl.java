package com.larimar.service.impl;

import com.larimar.entity.Comic;
import com.larimar.entity.Detail;
import com.larimar.entity.History;
import com.larimar.mapper.DetailMapper;
import com.larimar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Detail> queryComicDetail(Integer comicId) {
        return detailMapper.selectComicAllDetails(comicId);
    }

    @Override
    public List<Detail> selectComicDetailByType(String typeName) {
        return detailMapper.selectDetailsByType(typeName);
    }

    @Override
    public Detail getDetailById(Integer id) {
        return detailMapper.getDetailById(id);
    }
}
