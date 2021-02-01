package cn.syned.crm.commons.vo;

import cn.syned.crm.workbench.entity.ActivityRemark;
import lombok.Data;

import java.util.List;

@Data
public class ActivityRemarkVo {
    private boolean status;
    private List<ActivityRemark> dataList;
    private ActivityRemark data;
}
