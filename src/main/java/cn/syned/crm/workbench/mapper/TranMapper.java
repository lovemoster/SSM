package cn.syned.crm.workbench.mapper;

import cn.syned.crm.workbench.entity.Tran;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity cn.syned.crm.workbench.entity.Tran
 */
public interface TranMapper {
    /**
     * @mbg.generated 2021-02-18 20:39:07
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated 2021-02-18 20:39:07
     */
    int insert(Tran record);

    /**
     * @mbg.generated 2021-02-18 20:39:07
     */
    int insertSelective(Tran record);

    /**
     * @mbg.generated 2021-02-18 20:39:07
     */
    Tran selectByPrimaryKey(String id);

    /**
     * @mbg.generated 2021-02-18 20:39:07
     */
    int updateByPrimaryKeySelective(Tran record);

    /**
     * @mbg.generated 2021-02-18 20:39:07
     */
    int updateByPrimaryKey(Tran record);

    List<Tran> selectTranList(@Param(value = "customerIdList") List<String> customerIdList,
                              @Param(value = "contactsIdList") List<String> contactsIdList,
                              @Param(value = "tran") Tran tran);
}