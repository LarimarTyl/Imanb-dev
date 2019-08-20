package com.larimar.mapper;

import com.larimar.entity.Detail;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/20 周二 19:29
 */
public interface DetailMapper {
    /**
     * 添加章节详情
     * @param detail 章节详情对象
     * @return 增加数量
     */
    public int addDetail(Detail detail);

    /**
     * 删除章节详情
     * @param detailId 详情页id
     * @return 删除数量
     */
    public int delDetail(Integer detailId);

    /**
     * 更新章节信息
     * @param detail 章节详情对象
     * @return 更新数量
     */
    public  int updateDetailUpdateTime(Detail detail);

    /**
     * 查找漫画的章节详细信息
     * @param comicId 漫画id
     * @return 漫画章节详情集合
     */
    public List<Detail> selectAllDetails(Integer comicId);

    /**
     * 获取最新的章节更新
     * @param comicId 漫画id
     * @return 章节详情页
     */
    public Detail getNewDetails(Integer comicId);

    /**
     * 根据时间查找漫画详情
     * @param time 时间
     * @return 章节对象集合
     */
    public List<Detail> selectDetailsByTime(String time);

    /**
     * 根据类型查找漫画详情
     * @param type 类型名
     * @return 漫画详情集合
     */
    public List<Detail> selectDetailsByType(String type);

}
