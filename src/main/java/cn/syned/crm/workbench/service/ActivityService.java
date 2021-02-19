package cn.syned.crm.workbench.service;

import cn.syned.crm.commons.vo.ActivityVo;
import cn.syned.crm.workbench.entity.Activity;

import javax.servlet.http.HttpSession;

public interface ActivityService {

    ActivityVo queryActivityList(Integer pageNum, Integer pageSize, Activity activity);

    int addActivity(Activity activity, HttpSession session);

    Activity queryActivity(String id);

    int editActivity(Activity activity, HttpSession session);

    ActivityVo deleteActivity(String id);

    ActivityVo queryActivityByName(String name);
}
