package com.larimar.mapper;

import com.larimar.entity.History;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/20 周二 19:29
 */
public interface HistoryMapper {
    /**
     * 添加阅读历史
     * @param history 阅读历史对象
     * @return 添加数量
     */
    public int addHistory(History history);

    /**
     * 删除阅读历史
     * @param historyId 阅读历史id
     * @return 删除数量
     */
    public int delHistory(Integer historyId);

    /**
     * 更细阅读历史
     * @param history 阅读历史对象
     *  history里面存放了 用户id 漫画id 章节id 最后一次阅读时间 历史状态（用户更新后也可以走这个方法更新阅读状态）
     * @return 更新阅读数
     */
    public int updateHistory(History history);

    /**
     * 更改阅读历史状态（是否更新）
     * // FIXME: 2019/8/20  这里不需要考虑用户 直接修改对应漫画的所有历史记录的状态为已更新
     * @param comicId 漫画id
     * @return 更新数量
     */
    public int updateHistoryStatus(Integer comicId);

    /**
     * 查找用户所有阅读历史
     * @param userId 用户id
     * @return 历史记录集合
     */
    public List<History> selectAllHistory(Integer userId);

    /**
     * 查询用户对应状态的历史记录
     * @param userId 用户id
     * @param status 状态码
     * @return 历史记录集合
     */
    public List<History> selectHistoryByStatus(Integer userId,Integer status);
}
