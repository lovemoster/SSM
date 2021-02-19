package cn.syned.crm.settings.mapper;

import cn.syned.crm.settings.entity.DicType;

import java.util.List;

public interface DicTypeMapper {

    int deleteByPrimaryKey(String code);

    int insert(DicType record);

    int insertSelective(DicType record);

    DicType selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(DicType record);

    int updateByPrimaryKey(DicType record);

    List<DicType> selectAllDictionaryType();

}