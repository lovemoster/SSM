package cn.syned.crm.settings.mapper;

import cn.syned.crm.settings.entity.DicValue;

import java.util.List;

/**
 * @Entity generate.TblDicValue
 */
public interface DicValueMapper {
    /**
     * @mbg.generated 2021-02-02 15:08:43
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated 2021-02-02 15:08:43
     */
    int insert(DicValue record);

    /**
     * @mbg.generated 2021-02-02 15:08:43
     */
    int insertSelective(DicValue record);

    /**
     * @mbg.generated 2021-02-02 15:08:43
     */
    DicValue selectByPrimaryKey(String id);

    /**
     * @mbg.generated 2021-02-02 15:08:43
     */
    int updateByPrimaryKeySelective(DicValue record);

    /**
     * @mbg.generated 2021-02-02 15:08:43
     */
    int updateByPrimaryKey(DicValue record);

    List<DicValue> selectAllDictionaryValue();

    int deleteByPrimaryKeys(String[] ids);

    Integer selectDictionaryValueOrderNo(String typeCode);

    List<DicValue> selectAllDictionaryValueById(String id);

    List<DicValue> selectDictionaryValueByTypeCode(String typeCode);
}