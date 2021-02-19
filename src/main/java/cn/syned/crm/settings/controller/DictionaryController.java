package cn.syned.crm.settings.controller;

import cn.syned.crm.commons.vo.DictionaryVo;
import cn.syned.crm.settings.entity.DicType;
import cn.syned.crm.settings.entity.DicValue;
import cn.syned.crm.settings.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/settings/dictionary")
public class DictionaryController {

    private DictionaryService dictionaryService;

    @Autowired
    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    /**
     * @return
     */
    @GetMapping(path = "/type/queryAllDictionaryType")
    @ResponseBody
    public DictionaryVo<DicType> queryAllDictionaryType(
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        DictionaryVo<DicType> vo = dictionaryService.queryAllDictionaryType(pageNum, pageSize);
        return vo;
    }

    //========================================================================================

    @GetMapping(path = "/value/queryAllDictionaryValue")
    @ResponseBody
    public DictionaryVo<DicValue> queryAllDictionaryValue(
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        DictionaryVo<DicValue> vo = dictionaryService.queryAllDictionaryValue(pageNum, pageSize);
        return vo;
    }

    @GetMapping(path = "/value/queryAllDictionaryValueById")
    @ResponseBody
    public DictionaryVo<DicValue> queryAllDictionaryValueById(@RequestParam(name = "id") String id) {
        DictionaryVo<DicValue> vo = dictionaryService.queryAllDictionaryValueById(id);
        return vo;
    }

    @PostMapping(path = "/value/addDictionaryValue")
    @ResponseBody
    public DictionaryVo<DicValue> addDictionaryValue(DicValue dicValue) {
        DictionaryVo<DicValue> vo = dictionaryService.addDictionaryValue(dicValue);
        return vo;
    }

    @PostMapping(path = "/value/deleteDictionaryValue")
    @ResponseBody
    public DictionaryVo<DicValue> deleteDictionaryValue(@RequestParam(name = "id") String id) {
        DictionaryVo<DicValue> vo = dictionaryService.deleteDictionaryValue(id);
        return vo;
    }

    @GetMapping(path = "/value/queryDictionaryValueOrderNo")
    @ResponseBody
    public DictionaryVo<DicValue> queryDictionaryValueOrderNo(@RequestParam(name = "typeCode") String typeCode) {
        DictionaryVo<DicValue> vo = dictionaryService.queryDictionaryValueOrderNo(typeCode);
        return vo;
    }

    @PostMapping(path = "/value/updateDictionaryValueById")
    @ResponseBody
    public DictionaryVo<DicValue> updateDictionaryValueById(DicValue dicValue) {
        DictionaryVo<DicValue> vo = dictionaryService.updateDictionaryValueById(dicValue);
        return vo;
    }

    /**
     * 根据类型代码查询字典值列表
     *
     * @param typeCode
     * @return
     */
    @GetMapping(path = "/value/queryDictionaryValueByTypeCode")
    @ResponseBody
    public DictionaryVo<DicValue> queryDictionaryValueByTypeCode(@RequestParam(name = "typeCode") String typeCode) {
        DictionaryVo<DicValue> vo = dictionaryService.queryDictionaryValueByTypeCode(typeCode);
        return vo;
    }
}
