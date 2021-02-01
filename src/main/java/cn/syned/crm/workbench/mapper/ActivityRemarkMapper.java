package cn.syned.crm.workbench.mapper;

import cn.syned.crm.workbench.entity.ActivityRemark;

import java.util.List;

public interface ActivityRemarkMapper {

    int deleteByPrimaryKey(String id);

    int deleteByActivityId(String[] id);

    int insert(ActivityRemark record);

    int insertSelective(ActivityRemark record);

    ActivityRemark selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActivityRemark record);

    int updateByPrimaryKey(ActivityRemark record);

    List<ActivityRemark> selectActivityRemarkByActivityId(String activityId);
}