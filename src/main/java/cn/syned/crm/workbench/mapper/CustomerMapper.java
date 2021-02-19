package cn.syned.crm.workbench.mapper;

import cn.syned.crm.workbench.entity.Customer;

import java.util.List;

/**
 * @Entity cn.syned.crm.workbench.entity.Customer
 */
public interface CustomerMapper {
    /**
     * @mbg.generated 2021-02-18 20:57:19
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated 2021-02-18 20:57:19
     */
    int insert(Customer record);

    /**
     * @mbg.generated 2021-02-18 20:57:19
     */
    int insertSelective(Customer record);

    /**
     * @mbg.generated 2021-02-18 20:57:19
     */
    Customer selectByPrimaryKey(String id);

    /**
     * @mbg.generated 2021-02-18 20:57:19
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     * @mbg.generated 2021-02-18 20:57:19
     */
    int updateByPrimaryKey(Customer record);

    List<Customer> selectCustomerByName(String name);
}