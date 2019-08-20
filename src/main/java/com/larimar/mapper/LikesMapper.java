package com.larimar.mapper;

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
}
