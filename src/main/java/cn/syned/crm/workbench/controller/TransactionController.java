package cn.syned.crm.workbench.controller;

import cn.syned.crm.commons.exception.TranException;
import cn.syned.crm.commons.message.TranMessage;
import cn.syned.crm.commons.vo.Stage;
import cn.syned.crm.commons.vo.TranVo;
import cn.syned.crm.workbench.entity.Tran;
import cn.syned.crm.workbench.entity.TranHistory;
import cn.syned.crm.workbench.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/workbench/transaction")
public class TransactionController {
    private TransactionService transactionService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(path = "/queryTransactionList")
    @ResponseBody
    public TranVo<List<Tran>> queryTransactionList(@RequestParam(name = "pageNum", required = false) Integer pageNum,
                                                   @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                   Tran tran) {
        TranVo<List<Tran>> tranVo = new TranVo<>();
        try {
            List<Tran> tranList = transactionService.queryTransactionList(pageNum, pageSize, tran);
            tranVo.setCode(TranMessage.TRAN_MESSAGE_SUCCESS.getCode());
            tranVo.setMessage(TranMessage.TRAN_MESSAGE_SUCCESS.getMessage());
            tranVo.setData(tranList);
        } catch (TranException e) {
            tranVo.setCode(e.getCode());
            tranVo.setMessage(e.getMessage());
            tranVo.setData(new ArrayList<>());
        }
        return tranVo;
    }

    @GetMapping(path = "/queryTransactionById")
    @ResponseBody
    public TranVo<Tran> queryTransactionById(@RequestParam(name = "id") String id) {
        TranVo<Tran> tranVo = new TranVo<>();
        try {
            Tran tran = transactionService.queryTransactionById(id);
            List<Stage> stageList = transactionService.queryStageList(tran);
            tranVo.setCode(TranMessage.TRAN_MESSAGE_SUCCESS.getCode());
            tranVo.setMessage(TranMessage.TRAN_MESSAGE_SUCCESS.getMessage());
            tranVo.setData(tran);
            tranVo.setStageList(stageList);
        } catch (TranException e) {
            tranVo.setCode(e.getCode());
            tranVo.setMessage(e.getMessage());
        }
        return tranVo;
    }

    @PostMapping(path = "/updateStageList")
    @ResponseBody
    public TranVo<Tran> updateStageList(Tran tran, HttpSession session) {
        TranVo<Tran> tranVo = new TranVo<>();
        try {
            transactionService.updateStageList(tran, session);
            tranVo.setCode(TranMessage.TRAN_MESSAGE_SUCCESS.getCode());
            tranVo.setMessage(TranMessage.TRAN_MESSAGE_SUCCESS.getMessage());
        } catch (TranException e) {
            tranVo.setCode(e.getCode());
            tranVo.setMessage(e.getMessage());
        }
        return tranVo;
    }

    @GetMapping(path = "/queryTransactionHistory")
    @ResponseBody
    public TranVo<List<TranHistory>> queryTransactionHistory(@RequestParam(name = "tranId") String tranId) {
        TranVo<List<TranHistory>> tranVo = new TranVo<>();
        try {
            List<TranHistory> tranHistoryList = transactionService.queryTransactionHistoryList(tranId);
            tranVo.setCode(TranMessage.TRAN_MESSAGE_SUCCESS.getCode());
            tranVo.setMessage(TranMessage.TRAN_MESSAGE_SUCCESS.getMessage());
            tranVo.setData(tranHistoryList);
        } catch (TranException e) {
            tranVo.setCode(e.getCode());
            tranVo.setMessage(e.getMessage());
        }
        return tranVo;
    }

}
