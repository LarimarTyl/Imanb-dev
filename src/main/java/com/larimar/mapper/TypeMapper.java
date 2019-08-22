package com.larimar.mapper;

import com.larimar.entity.Types;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/20 周二 19:31
 */
public interface TypeMapper {
    /**
     * 添加类型名
     * @param types 类型
     * @return 添加数量
     */
    public int addType(Types types);

    /**
     * 删除类型名
     * @param typeName 类型名
     * @return 删除数量
     */
    public int delType(String typeName);

    /**
     * 查找所有类型名
     * @return 类型名集合
     */
    public List<Types> selectAllTypes();
}
