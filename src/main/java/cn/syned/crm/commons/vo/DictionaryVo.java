package cn.syned.crm.commons.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Data
@Component
public class DictionaryVo<T> implements Serializable {
    private String status;
    private int code;
    private List<T> data;
    private Integer orderNo;
    private String message;
    private String errorMessage;
}
