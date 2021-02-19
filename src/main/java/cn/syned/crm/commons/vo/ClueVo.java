package cn.syned.crm.commons.vo;

import cn.syned.crm.workbench.entity.Clue;
import lombok.Data;

import java.util.List;

@Data
public class ClueVo {
    private Integer code;
    private String message;
    private List<Clue> data;
}
