package cn.syned.crm.commons.vo;

import lombok.Data;

import java.util.List;

@Data
public class TranVo<T> {
    private Integer code;
    private String message;
    private T data;
    private List<Stage> stageList;
}
