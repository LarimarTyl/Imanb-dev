package com.larimar.service.impl;

import com.larimar.entity.History;
import com.larimar.mapper.DetailMapper;
import com.larimar.mapper.HistoryMapper;
import com.larimar.selectPojo.HistorySelect;
import com.larimar.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.larimar.util.DateUtil.localDateToString;

/**
 * @author Larimar
 * @time 2019/8/26 周一 14:52
 */
@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryMapper historyMapper;
    @Autowired
    DetailMapper detailMapper;

    @Override
    public boolean addHistory(History history) {
        if (historyMapper.selectByUserAndComic(history.getUserId(),history.getComicId())==null){
            return historyMapper.addHistory(history)>0;
        }else {
            //存在的话就只改原历史记录章节，时间，和状态
            History exitHistory = historyMapper.selectByUserAndComic(history.getUserId(), history.getComicId());
            exitHistory.setDetailId(history.getDetailId());
            //不是最新章节
            if (!history.getDetailId().equals(detailMapper.getNewest(history.getComicId()).getDetailId())){
                exitHistory.setStatus(0);
            }else {
                exitHistory.setStatus(-1);
            }
            exitHistory.setLastReadTime(localDateToString());
            historyMapper.updateHistory(exitHistory);
            return false;
        }
    }

    @Override
    public boolean delHistory(Integer historyId) {
        return historyMapper.delHistory(historyId)>0;
    }

    @Override
    public boolean updateHistory(History history) {
        return historyMapper.updateHistory(history)>0;
    }

    @Override
    public boolean updateHistoryStatus(Integer comicId, Integer status) {
        return historyMapper.updateHistoryStatus(comicId,status)>0;
    }

    @Override
    public History selectHistoryById(Integer id) {
        return historyMapper.selectHistoryById(id);
    }

    @Override
    public History selectHistoryByUserAndComic(Integer userId, Integer comicId) {
        return historyMapper.selectByUserAndComic(userId,comicId);
    }

    @Override
    public List<History> queryAllHistory() {
        return historyMapper.selectAllHistory();
    }

    @Override
    public List<History> selectByOption(HistorySelect historySelect) {
        return historyMapper.selectByOptions(historySelect);
    }

    @Override
    public List<History> selectUsersAllHistory(Integer userId) {
        return historyMapper.selectUsersAllHistory(userId);
    }

    @Override
    public List<History> selectUsersHistoryByStatus(Integer userId, Integer status) {
        return historyMapper.selectHistoryByStatus(userId, status);
    }
}
