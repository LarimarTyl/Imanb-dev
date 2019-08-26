package com.larimar.service;

import com.larimar.entity.History;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/26 周一 14:53
 */
public interface HistoryService {
    public boolean addHistory(History history);
    public boolean delHistory(Integer historyId);
    public boolean updateHistory(History history);
    public boolean updateHistoryStatus(Integer comicId,Integer status);
    public List<History> queryAllHistory();
    public List<History> selectUsersAllHistory(Integer userId);
    public List<History> selectUsersHistoryByStatus(Integer userId,Integer status);
}
