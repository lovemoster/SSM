package cn.syned.crm.workbench.mapper;

import cn.syned.crm.workbench.entity.Clue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity cn.syned.crm.workbench.entity.Clue
 */
public interface ClueMapper {
    /**
     * @mbg.generated 2021-02-13 17:38:53
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated 2021-02-13 17:38:53
     */
    int insert(Clue record);

    /**
     * @mbg.generated 2021-02-13 17:38:53
     */
    int insertSelective(Clue record);

    /**
     * @mbg.generated 2021-02-13 17:38:53
     */
    Clue selectByPrimaryKey(String id);

    /**
     * @mbg.generated 2021-02-13 17:38:53
     */
    int updateByPrimaryKeySelective(Clue record);

    /**
     * @mbg.generated 2021-02-13 17:38:53
     */
    int updateByPrimaryKey(Clue record);

    List<Clue> selectClueList(@Param(value = "clue") Clue clue,
                              @Param(value = "ownerList") List<String> ownerList);
}