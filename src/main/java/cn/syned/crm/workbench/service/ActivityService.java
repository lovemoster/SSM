package cn.syned.crm.workbench.service;

import cn.syned.crm.commons.vo.ActivityVo;
import cn.syned.crm.workbench.entity.Activity;

public interface ActivityService {

    ActivityVo queryActivityList(Integer pageNum, Integer pageSize, Activity activity);

    int addActivity(Activity activity);
}
