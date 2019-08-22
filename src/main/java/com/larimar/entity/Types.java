package com.larimar.entity;

/**
 * @author Larimar
 * @time 2019/8/20 周二 19:25
 */
public class Types {
    private Integer typeId;//类型id
    private String typeName;//类型名

    public Types() {
    }

    public Types(Integer typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Type{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
