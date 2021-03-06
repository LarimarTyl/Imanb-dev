package com.larimar.service;

import com.larimar.entity.History;
import com.larimar.selectPojo.HistorySelect;

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
    public History selectHistoryById(Integer id);
    public History selectHistoryByUserAndComic(Integer userId,Integer comicId);
    public List<History> queryAllHistory();
    public List<History> selectByOption(HistorySelect historySelect);
    public List<History> selectUsersAllHistory(Integer userId);
    public List<History> selectUsersHistoryByStatus(Integer userId,Integer status);
}
