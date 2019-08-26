package com.larimar.mapper;

import com.larimar.entity.Comic;
import net.sf.jsqlparser.statement.Commit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/20 周二 19:28
 */
public interface ComicMapper {
    /**
     * 增加漫画
     * @param comic 漫画对象
     * @return 增加数量
     */
    public int addComic(Comic comic);

    /**
     * 删除漫画
     * @param comicId 漫画id
     * @return 删除数量
     */
    public int delComic(Integer comicId);

    /**
     * 修改漫画信息
     * @param comic 漫画对象
     * @return 修改数量
     */
    public int updateComic(Comic comic);

    /**
     * 修改漫画状态
     * @param comicId 漫画id
     * @param status 漫画状态
     * @return 修改数量
     */
    public int updateComicStatus(@Param("comicId") Integer comicId,@Param("status") Integer status);

    /**
     * 修改漫画最新更新信息
     * @param comicId 漫画id
     * @param newUpdateTime 最新更新时间
     * @param newChapter 最新章节
     * @param status 漫画状态
     * @return 修改数量
     */
    public int updateComicNewsUpdateTime(@Param("comicId")Integer comicId,@Param("newUpdateTime") String newUpdateTime,@Param("newChapter") String newChapter);

    /**
     * 根据漫画id查找漫画
     * @param comicId 漫画id
     * @return 漫画对象
     */
    public Comic selectComicById(Integer comicId);

    /**
     * 根据漫画名字查找漫画
     * @param name 漫画名
     * @return 漫画集合
     */
    public List<Comic> selectComicByName(String name);

    /**
     * 根据漫画类型查找漫画
     * @param typeName 类型名
     * @return 漫画集合
     */
    public List<Comic> selectComicByTypeName(String typeName);

    /**
     * 根据作者名字查找漫画
     * @param authorName 作者名字
     * @return 漫画集合
     */
    public List<Comic> selectComicByAuthor(String authorName);

    /**
     * 根据漫画状态查找漫画
     * @param status 漫画状态
     * @return 漫画集合
     */
    public List<Comic> selectComicByStatus(Integer status);

    /**
     * 根据地区查找漫画
     * @param location 地区
     * @return 漫画集合
     */
    public List<Comic> selectComicByLocation(String location);

    /**
     * 根据订阅数查找漫画
     * @return 漫画集合
     */
    public List<Comic> selectComicByOrder();
    /**
     * 查找最新漫画
     * @return 漫画集合
     */
    public List<Comic> selectNewComics();
    /**
     * 查找人气漫画
     * @return 漫画集合
     */
    public List<Comic> selectComicsByLikes();

    /**
     * 查找用户订阅漫画
     * @param userId 用户id
     * @return 漫画集合
     */
    public List<Comic> selectOrderComic(Integer userId);

    /**
     * 查找所有漫画
     * @return 漫画集合
     */
    public List<Comic> selectAllComics();
}
