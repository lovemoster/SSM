package cn.syned.crm.workbench.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Activity implements Serializable {

    private String id;

    private String owner;

    private String name;

    private String startDate;

    private String endDate;

    private String cost;

    private String description;

    private String createTime;

    private String createBy;

    private String editTime;

    private String editBy;

    private static final long serialVersionUID = 1L;
}