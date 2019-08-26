package com.larimar.service.impl;

import com.larimar.entity.History;
import com.larimar.mapper.HistoryMapper;
import com.larimar.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/26 周一 14:52
 */
@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryMapper historyMapper;

    @Override
    public boolean addHistory(History history) {
        return historyMapper.addHistory(history)>0;
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
    public List<History> queryAllHistory() {
        return historyMapper.selectAllHistory();
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
