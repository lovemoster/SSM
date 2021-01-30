package cn.syned.crm.workbench.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.syned.crm.commons.vo.ActivityVo;
import cn.syned.crm.settings.entity.User;
import cn.syned.crm.workbench.entity.Activity;
import cn.syned.crm.workbench.mapper.ActivityMapper;
import cn.syned.crm.workbench.service.ActivityService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public ActivityVo queryActivityList(Integer pageNum, Integer pageSize, Activity activity) {
        //判断pageSize和pageNum是否为空
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);

        List<Activity> activityList = activityMapper.selectActivityList(activity);
        Integer totalNum = activityMapper.selectActivityListNum(activity);
        ActivityVo activityVo = new ActivityVo();
        activityVo.setTotalNum(totalNum);
        activityVo.setPageSize(pageSize);
        activityVo.setData(activityList);

        return activityVo;
    }

    @Override
    public int addActivity(Activity activity, HttpSession session) {
        User user = (User) session.getAttribute("user");
        activity.setCreateBy(user.getName());
        activity.setId(SecureUtil.simpleUUID());
        activity.setCreateTime(DateUtil.formatDate(new Date()));
        return activityMapper.insert(activity);
    }

    @Override
    public Activity queryActivity(String id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int editActivity(Activity activity, HttpSession session) {
        User user = (User) session.getAttribute("user");
        activity.setEditBy(user.getName());
        activity.setEditTime(DateUtil.formatDate(new Date()));
        return activityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    public ActivityVo deleteActivity(String id) {
        String[] ids = id.split(",");
        int count = activityMapper.deleteByPrimaryKeys(ids);
        ActivityVo activityVo = new ActivityVo();
        if (count != ids.length) {
            activityVo.setStatus("false");
        } else {
            activityVo.setStatus("true");
        }
        return activityVo;
    }
}
