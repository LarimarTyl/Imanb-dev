package com.larimar.mapper;

import com.larimar.entity.Likes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/20 周二 19:30
 */
public interface LikesMapper {
    /**
     * 添加漫画点赞
     * @param comicId 漫画id
     * @return 添加数量
     */
    public int addComicLikes(Integer comicId);

    /**
     * 添加章节点赞
     * @param detailId 章节id
     * @return 添加数量
     */
    public int addDetailLikes(Integer detailId);

    /**
     * 删除章节点赞
     * @param detailId 章节id
     * @return 删除数量
     */
    public int delDetailLikes(Integer detailId);

    /**
     * 删除漫画点赞
     * @param comicId 漫画id
     * @return 删除数量
     */
    public int delComicLikes(Integer comicId);

    /**
     * 修改漫画点赞数
     * @param comicId 漫画id
     * @param number 点赞数
     * @return 修改数量
     */
    public int updateComicLikes(@Param("comicId") Integer comicId,@Param("number") Integer number);

    /**
     * 修改章节点赞数
     * @param detailId 章节id
     * @param number 点赞数
     * @return 修改数量
     */
    public int updateDetailLikes(@Param("detailId")Integer detailId,@Param("number") Integer number);

    /**
     * 查找章节点赞数
     * @param detailId 章节id
     * @return 点赞数
     */
    public int selectDetailLikes(Integer detailId);

    /**
     * 漫画点赞数
     * @param comicId 漫画id
     * @return 点赞数
     */
    public int selectComicLikes(Integer comicId);

    public List<Likes> queryAllLikes();
}
