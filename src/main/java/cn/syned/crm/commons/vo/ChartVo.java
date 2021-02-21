package cn.syned.crm.commons.vo;

import lombok.Data;

@Data
public class ChartVo<T> {
    private Integer code;
    private String message;
    private T data;
}
