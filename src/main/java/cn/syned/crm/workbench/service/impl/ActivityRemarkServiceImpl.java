package cn.syned.crm.workbench.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.syned.crm.commons.vo.ActivityRemarkVo;
import cn.syned.crm.settings.entity.User;
import cn.syned.crm.workbench.entity.ActivityRemark;
import cn.syned.crm.workbench.mapper.ActivityRemarkMapper;
import cn.syned.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    private ActivityRemarkMapper activityRemarkMapper;

    @Autowired
    public void setActivityRemarkMapper(ActivityRemarkMapper activityRemarkMapper) {
        this.activityRemarkMapper = activityRemarkMapper;
    }

    @Override
    public ActivityRemarkVo queryActivityRemark(String activityId) {
        List<ActivityRemark> activityRemarkList = activityRemarkMapper.selectActivityRemarkByActivityId(activityId);
        ActivityRemarkVo activityRemarkVo = new ActivityRemarkVo();
        activityRemarkVo.setStatus(true);
        activityRemarkVo.setDataList(activityRemarkList);
        return activityRemarkVo;
    }

    @Override
    public ActivityRemarkVo addActivityRemark(ActivityRemark activityRemark, HttpSession session) {
        //生成UUID
        String uuid = SecureUtil.simpleUUID();
        //设置UUID
        activityRemark.setId(uuid);
        //设置编辑标记
        activityRemark.setEditFlag("0");
        //设置创建时间
        activityRemark.setCreateTime(DateUtil.formatDateTime(new Date()));
        //获取当前用户id
        User user = (User) session.getAttribute("user");
        activityRemark.setCreateBy(user.getId());
        //调用业务方法
        int count = activityRemarkMapper.insertSelective(activityRemark);
        ActivityRemarkVo activityRemarkVo = new ActivityRemarkVo();
        //判断是否添加成功
        if (count == 1) {
            activityRemarkVo.setStatus(true);
            ActivityRemark newActivityRemark = activityRemarkMapper.selectByPrimaryKey(uuid);
            activityRemarkVo.setData(newActivityRemark);
        } else {
            activityRemarkVo.setStatus(false);
        }
        return activityRemarkVo;
    }

    @Override
    public ActivityRemarkVo queryActivityRemarkById(String id) {
        ActivityRemark activityRemark = activityRemarkMapper.selectByPrimaryKey(id);
        ActivityRemarkVo activityRemarkVo = new ActivityRemarkVo();
        activityRemarkVo.setStatus(true);
        activityRemarkVo.setData(activityRemark);
        return activityRemarkVo;
    }

    @Override
    public ActivityRemarkVo updateActivityRemark(ActivityRemark activityRemark, HttpSession session) {
        //修改编辑标记
        activityRemark.setEditFlag("1");
        //设置修改者的ID
        User user = (User) session.getAttribute("user");
        activityRemark.setEditBy(user.getId());
        //设置修改时间
        activityRemark.setEditTime(DateUtil.formatDateTime(new Date()));
        int count = activityRemarkMapper.updateByPrimaryKeySelective(activityRemark);
        ActivityRemarkVo activityRemarkVo = new ActivityRemarkVo();
        activityRemarkVo.setStatus(count == 1);
        return activityRemarkVo;
    }

    @Override
    public ActivityRemarkVo deleteActivityRemark(String id) {
        int count = activityRemarkMapper.deleteByPrimaryKey(id);
        ActivityRemarkVo activityRemarkVo = new ActivityRemarkVo();
        activityRemarkVo.setStatus(count == 1);
        return activityRemarkVo;
    }
}
