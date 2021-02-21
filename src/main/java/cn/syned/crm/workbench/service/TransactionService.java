package cn.syned.crm.workbench.service;

import cn.syned.crm.commons.exception.TranException;
import cn.syned.crm.commons.vo.Stage;
import cn.syned.crm.workbench.entity.Tran;
import cn.syned.crm.workbench.entity.TranHistory;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface TransactionService {
    /**
     * 查询交易列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @param tran     交易对象
     * @return 交易列表
     * @throws TranException 交易模块异常
     */
    List<Tran> queryTransactionList(Integer pageNum, Integer pageSize, Tran tran) throws TranException;

    Tran queryTransactionById(String id) throws TranException;

    List<Stage> queryStageList(Tran tran) throws TranException;

    void updateStageList(Tran tran, HttpSession session) throws TranException;

    List<TranHistory> queryTransactionHistoryList(String tranId) throws TranException;
}
