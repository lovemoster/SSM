package cn.syned.crm.workbench.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * null
 *
 * @TableName tbl_tran_history
 */
@Data
public class TranHistory implements Serializable {
    /**
     * @mbg.generated 2021-02-20 21:36:31
     */
    private String id;

    /**
     * @mbg.generated 2021-02-20 21:36:31
     */
    private String stage;

    /**
     * @mbg.generated 2021-02-20 21:36:31
     */
    private String money;

    /**
     * @mbg.generated 2021-02-20 21:36:31
     */
    private String expectedDate;

    /**
     * @mbg.generated 2021-02-20 21:36:31
     */
    private String createTime;

    /**
     * @mbg.generated 2021-02-20 21:36:31
     */
    private String createBy;

    /**
     * @mbg.generated 2021-02-20 21:36:31
     */
    private String tranId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tbl_tran_history
     *
     * @mbg.generated 2021-02-20 21:36:31
     */

    private String possibility;

    private static final long serialVersionUID = 1L;
}