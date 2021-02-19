package cn.syned.crm.workbench.service;

import cn.syned.crm.commons.vo.ClueVo;
import cn.syned.crm.workbench.entity.Clue;
import cn.syned.crm.workbench.entity.Tran;

import javax.servlet.http.HttpSession;

public interface ClueService {
    ClueVo queryClueList(Integer pageNum, Integer pageSize, Clue clue);

    ClueVo queryClueById(String id);

    ClueVo convertClue(Tran tran, String id, Boolean flag, HttpSession session);
}
