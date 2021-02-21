package cn.syned.crm.workbench.mapper;

import cn.syned.crm.workbench.entity.Contacts;

import java.util.List;

/**
 * @Entity cn.syned.crm.workbench.entity.Contacts
 */
public interface ContactsMapper {
    /**
     * @mbg.generated 2021-02-18 20:56:39
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated 2021-02-18 20:56:39
     */
    int insert(Contacts record);

    /**
     * @mbg.generated 2021-02-18 20:56:39
     */
    int insertSelective(Contacts record);

    /**
     * @mbg.generated 2021-02-18 20:56:39
     */
    Contacts selectByPrimaryKey(String id);

    /**
     * @mbg.generated 2021-02-18 20:56:39
     */
    int updateByPrimaryKeySelective(Contacts record);

    /**
     * @mbg.generated 2021-02-18 20:56:39
     */
    int updateByPrimaryKey(Contacts record);

    List<Contacts> selectCustomerByName(String contactsId);
}