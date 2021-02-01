package cn.syned.crm.workbench.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityRemark implements Serializable {

    private String id;

    private String noteContent;

    private String createTime;

    private String createBy;

    private String editTime;

    private String editBy;

    private String editFlag;

    private String activityId;

    private static final long serialVersionUID = 1L;
}