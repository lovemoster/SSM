package cn.syned.crm.commons.vo;

import lombok.Data;

@Data
public class Stage {
    private String id;
    private Integer index;
    private String text;
    private String type;
    private String possibility;
    private boolean anchorPoint;
}
