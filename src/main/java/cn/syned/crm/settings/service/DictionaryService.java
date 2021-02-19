package cn.syned.crm.settings.service;

import cn.syned.crm.commons.vo.DictionaryVo;
import cn.syned.crm.settings.entity.DicType;
import cn.syned.crm.settings.entity.DicValue;

public interface DictionaryService {
    DictionaryVo<DicType> queryAllDictionaryType(Integer pageNum, Integer pageSize);

    DictionaryVo<DicValue> queryAllDictionaryValue(Integer pageNum, Integer pageSize);

    DictionaryVo<DicValue> addDictionaryValue(DicValue dicValue);

    DictionaryVo<DicValue> deleteDictionaryValue(String id);

    DictionaryVo<DicValue> queryDictionaryValueOrderNo(String typeCode);

    DictionaryVo<DicValue> updateDictionaryValueById(DicValue dicValue);

    DictionaryVo<DicValue> queryAllDictionaryValueById(String id);

    DictionaryVo<DicValue> queryDictionaryValueByTypeCode(String typeCode);
}
