package cn.syned.crm.commons.vo;

import cn.syned.crm.workbench.entity.Activity;
import lombok.Data;

import java.util.List;

@Data
public class ActivityVo {
    private Integer totalNum;
    private Integer pageSize;
    private List<Activity> data;
    private String status;
}
