package cn.syned.crm.settings.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.syned.crm.commons.vo.DictionaryVo;
import cn.syned.crm.settings.entity.DicType;
import cn.syned.crm.settings.entity.DicValue;
import cn.syned.crm.settings.mapper.DicTypeMapper;
import cn.syned.crm.settings.mapper.DicValueMapper;
import cn.syned.crm.settings.service.DictionaryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private DicTypeMapper dicTypeMapper;
    private DicValueMapper dicValueMapper;

    @Autowired
    public void setDicTypeMapper(DicTypeMapper dicTypeMapper) {
        this.dicTypeMapper = dicTypeMapper;
    }

    @Autowired
    public void setDicValueMapper(DicValueMapper dicValueMapper) {
        this.dicValueMapper = dicValueMapper;
    }

    @Override
    public DictionaryVo<DicType> queryAllDictionaryType(Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<DicType> dicTypes = dicTypeMapper.selectAllDictionaryType();
        DictionaryVo<DicType> vo = new DictionaryVo<>();
        vo.setStatus("success");
        vo.setCode(200);
        vo.setData(dicTypes);
        return vo;
    }

    @Override
    public DictionaryVo<DicValue> queryAllDictionaryValue(Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<DicValue> dicValues = dicValueMapper.selectAllDictionaryValue();
        DictionaryVo<DicValue> vo = new DictionaryVo<>();
        vo.setStatus("success");
        vo.setCode(200);
        vo.setData(dicValues);
        return vo;
    }

    @Override
    public DictionaryVo<DicValue> addDictionaryValue(DicValue dicValue) {
        dicValue.setId(SecureUtil.simpleUUID());
        int count = dicValueMapper.insertSelective(dicValue);
        DictionaryVo<DicValue> vo = new DictionaryVo<>();
        if (count == 1) {
            vo.setCode(200);
            vo.setStatus("success");
        } else {
            vo.setStatus("error");
            vo.setCode(401);
        }
        return vo;
    }

    @Override
    public DictionaryVo<DicValue> deleteDictionaryValue(String id) {
        String[] ids = id.split(",");
        int count = dicValueMapper.deleteByPrimaryKeys(ids);
        DictionaryVo<DicValue> vo = new DictionaryVo<>();
        if (count == ids.length) {
            vo.setCode(200);
            vo.setStatus("success");
        } else {
            vo.setCode(401);
            vo.setStatus("error");
        }
        return vo;
    }

    @Override
    public DictionaryVo<DicValue> queryDictionaryValueOrderNo(String typeCode) {
        Integer num = dicValueMapper.selectDictionaryValueOrderNo(typeCode);
        DictionaryVo<DicValue> vo = new DictionaryVo<>();
        if (num != null) {
            vo.setCode(200);
            vo.setStatus("success");
            vo.setOrderNo(num + 1);
        } else {
            vo.setCode(401);
            vo.setStatus("error");
            vo.setErrorMessage("草泥马的瞎查什么");
        }

        return vo;
    }

    @Override
    public DictionaryVo<DicValue> updateDictionaryValueById(DicValue dicValue) {
        DictionaryVo<DicValue> vo = new DictionaryVo<>();
        if (StrUtil.isEmpty(dicValue.getId())) {
            vo.setCode(401);
            vo.setErrorMessage("草泥马的瞎查什么，连参数的都不带吗？");
            return vo;
        }
        int count = dicValueMapper.updateByPrimaryKeySelective(dicValue);
        if (count == 1) {
            vo.setCode(200);
            vo.setMessage("修改成功");
        } else {
            vo.setCode(401);
            vo.setErrorMessage("修改失败");
        }
        return vo;
    }

    @Override
    public DictionaryVo<DicValue> queryAllDictionaryValueById(String id) {
        DictionaryVo<DicValue> vo = new DictionaryVo<>();
        if (StrUtil.isEmpty(id)) {
            vo.setCode(401);
            vo.setErrorMessage("草泥马的瞎查什么，连参数的都不带吗？");
            return vo;
        }
        List<DicValue> dicValues = dicValueMapper.selectAllDictionaryValueById(id);
        if (dicValues != null) {
            vo.setCode(200);
            vo.setData(dicValues);
        } else {
            vo.setCode(401);
            vo.setErrorMessage("查询失败");
        }
        return vo;
    }

    @Override
    public DictionaryVo<DicValue> queryDictionaryValueByTypeCode(String typeCode) {
        DictionaryVo<DicValue> vo = new DictionaryVo<>();
        if (StrUtil.isEmpty(typeCode)) {
            vo.setCode(401);
            vo.setErrorMessage("草泥马的瞎查什么，连参数的都不带吗？");
            return vo;
        }
        List<DicValue> dicValues = dicValueMapper.selectDictionaryValueByTypeCode(typeCode);
        if (dicValues != null) {
            vo.setCode(200);
            vo.setData(dicValues);
        } else {
            vo.setCode(401);
            vo.setErrorMessage("查询失败");
        }
        return vo;
    }
}
