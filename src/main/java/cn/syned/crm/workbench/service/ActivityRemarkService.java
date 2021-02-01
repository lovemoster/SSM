package cn.syned.crm.workbench.service;

import cn.syned.crm.commons.vo.ActivityRemarkVo;
import cn.syned.crm.workbench.entity.ActivityRemark;

import javax.servlet.http.HttpSession;

public interface ActivityRemarkService {
    ActivityRemarkVo queryActivityRemark(String activityId);

    ActivityRemarkVo addActivityRemark(ActivityRemark activityRemark, HttpSession session);

    ActivityRemarkVo queryActivityRemarkById(String id);

    ActivityRemarkVo updateActivityRemark(ActivityRemark activityRemark, HttpSession session);

    ActivityRemarkVo deleteActivityRemark(String id);
}
