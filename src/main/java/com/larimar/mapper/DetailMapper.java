package com.larimar.mapper;

import com.larimar.entity.Detail;
import com.larimar.selectPojo.DetailSelect;
import org.apache.ibatis.annotations.Param;

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
    public  int updateDetail(Detail detail);

    /**
     * 查找漫画的章节详细信息
     * @param comicId 漫画id
     * @return 漫画章节详情集合
     */
    public List<Detail> selectComicAllDetails(Integer comicId);
    public List<Detail> selectAllDetails();
    public List<Detail> selectByOptions(DetailSelect detailSelect);

    // TODO: 2019/8/21 根据时间 查找当月更新用更新时间的字符串包含来实现 更新session就可以是实现

    /**
     * 根据类型查找漫画详情
     * @param type 类型名
     * @return 漫画详情集合
     */
    public List<Detail> selectDetailsByType(String type);

    public Detail getDetailById(Integer detailId);

    public Detail selectDetailByComicIdAndChapter(@Param("comicId") Integer comicId,@Param("chapterName") String chapterName);

}
