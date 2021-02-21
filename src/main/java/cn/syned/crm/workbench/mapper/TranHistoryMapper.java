package cn.syned.crm.workbench.mapper;

import cn.syned.crm.workbench.entity.TranHistory;

import java.util.List;

/**
 * @Entity cn.syned.crm.workbench.entity.TranHistory
 */
public interface TranHistoryMapper {
    /**
     * @mbg.generated 2021-02-20 21:36:31
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated 2021-02-20 21:36:31
     */
    int insert(TranHistory record);

    /**
     * @mbg.generated 2021-02-20 21:36:31
     */
    int insertSelective(TranHistory record);

    /**
     * @mbg.generated 2021-02-20 21:36:31
     */
    TranHistory selectByPrimaryKey(String id);

    /**
     * @mbg.generated 2021-02-20 21:36:31
     */
    int updateByPrimaryKeySelective(TranHistory record);

    /**
     * @mbg.generated 2021-02-20 21:36:31
     */
    int updateByPrimaryKey(TranHistory record);

    List<TranHistory> selectByTranId(String tranId);
}