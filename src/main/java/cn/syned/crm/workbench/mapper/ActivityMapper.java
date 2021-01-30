package cn.syned.crm.workbench.mapper;

import cn.syned.crm.workbench.entity.Activity;

import java.util.List;

public interface ActivityMapper {

    int deleteByPrimaryKey(String id);

    int deleteByPrimaryKeys(String[] id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    List<Activity> selectActivityList(Activity activity);

    Integer selectActivityListNum(Activity activity);
}