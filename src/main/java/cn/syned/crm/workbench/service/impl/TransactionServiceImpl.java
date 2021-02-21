package cn.syned.crm.workbench.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.syned.crm.commons.exception.TranException;
import cn.syned.crm.commons.message.TranMessage;
import cn.syned.crm.commons.vo.Stage;
import cn.syned.crm.settings.entity.DicValue;
import cn.syned.crm.settings.entity.User;
import cn.syned.crm.settings.mapper.DicValueMapper;
import cn.syned.crm.workbench.entity.Contacts;
import cn.syned.crm.workbench.entity.Customer;
import cn.syned.crm.workbench.entity.Tran;
import cn.syned.crm.workbench.entity.TranHistory;
import cn.syned.crm.workbench.mapper.ContactsMapper;
import cn.syned.crm.workbench.mapper.CustomerMapper;
import cn.syned.crm.workbench.mapper.TranHistoryMapper;
import cn.syned.crm.workbench.mapper.TranMapper;
import cn.syned.crm.workbench.service.TransactionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TranMapper tranMapper;
    private CustomerMapper customerMapper;
    private ContactsMapper contactsMapper;
    private DicValueMapper dicValueMapper;
    private TranHistoryMapper tranHistoryMapper;

    @Autowired
    public void setTranMapper(TranMapper tranMapper) {
        this.tranMapper = tranMapper;
    }

    @Autowired
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Autowired
    public void setContactsMapper(ContactsMapper contactsMapper) {
        this.contactsMapper = contactsMapper;
    }

    @Autowired
    public void setDicValueMapper(DicValueMapper dicValueMapper) {
        this.dicValueMapper = dicValueMapper;
    }

    @Autowired
    public void setTranHistoryMapper(TranHistoryMapper tranHistoryMapper) {
        this.tranHistoryMapper = tranHistoryMapper;
    }

    /**
     * 查询交易列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @param tran     交易对象
     * @return 交易列表
     * @throws TranException 交易模块异常
     */
    @Override
    public List<Tran> queryTransactionList(Integer pageNum, Integer pageSize, Tran tran) throws TranException {
        //如果pageNum为null且pageSize不为null时, 默认查询第一页
        if (pageNum == null && pageSize != null) {
            PageHelper.startPage(1, pageSize);
        }
        //如果pageNum不为null且pageSize为null时, 默认查询条数为10条
        if (pageNum != null && pageSize == null) {
            PageHelper.startPage(pageNum, 10);
        }
        //如果pageNum不为null且pageSize不为null时, 默认查询所有
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        //如果pageNum为null且pageSize为null时, 默认查询所有

        //客户名称不为空时查询交易列表
        List<String> customerIdList = null;
        if (StrUtil.isNotEmpty(tran.getCustomerId())) {
            //查询客户
            List<Customer> customerList = customerMapper.selectCustomerByName(tran.getCustomerId());
            if (customerList == null || customerList.size() < 1) {
                //如果没有此用户信息则返回空
                throw new TranException(TranMessage.TRAN_MESSAGE_SUCCESS);
            } else {
                customerIdList = new ArrayList<>();
                List<String> finalCustomerIdList = customerIdList;
                customerList.forEach(customer -> finalCustomerIdList.add(customer.getId()));
            }
        }

        //联系人名称不为空时查询交易列表
        List<String> contactsIdList = null;
        if (StrUtil.isNotEmpty(tran.getContactsId())) {
            //查询客户
            List<Contacts> contactsList = contactsMapper.selectCustomerByName(tran.getContactsId());
            if (contactsList == null || contactsList.size() < 1) {
                //如果没有此联系人信息则返回空
                throw new TranException(TranMessage.TRAN_MESSAGE_SUCCESS);
            } else {
                contactsIdList = new ArrayList<>();
                List<String> finalContactsIdList = contactsIdList;
                contactsList.forEach(contacts -> finalContactsIdList.add(contacts.getId()));
            }
        }

        //查询交易列表
        return tranMapper.selectTranList(customerIdList, contactsIdList, tran);
    }

    @Override
    public Tran queryTransactionById(String id) throws TranException {
        //判断ID值是否为空
        if (StrUtil.isEmpty(id)) {
            throw new TranException(TranMessage.TRAN_MESSAGE_PARAMETER_INCOMPLETE);
        }
        //通过ID查询交易记录
        return tranMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Stage> queryStageList(Tran tran) throws TranException {
        if (tran == null) {
            throw new TranException(TranMessage.TRAN_MESSAGE_PARAMETER_INCOMPLETE);
        }
        //获取阶段个数
        List<DicValue> dicValueStageList = dicValueMapper.selectDictionaryValueByTypeCode("stage");
        List<Stage> stageList = new ArrayList<>();
        ResourceBundle bundle = ResourceBundle.getBundle("base/possibility");
        //判断交易状态
        if ("0".equals(tran.getPossibility())) {
            if ("丢失的线索".equals(tran.getStage())) {
                dicValueStageList.forEach(dicValueStage -> {
                    Stage stage = new Stage();
                    stage.setId(dicValueStage.getId());
                    stage.setIndex(Integer.parseInt(dicValueStage.getOrderNo()) - 1);
                    stage.setText(dicValueStage.getText());
                    //判断图标类型
                    if (stage.getIndex() < 7) {
                        stage.setType("黑圈");
                    } else if (stage.getIndex() < 8) {
                        stage.setType("红叉");
                    } else {
                        stage.setType("黑叉");
                    }
                    //判断可能性
                    stage.setPossibility(bundle.getString(dicValueStage.getText()));
                    stage.setAnchorPoint(false);
                    stageList.add(stage);
                });
            } else if ("因竞争丢失关闭".equals(tran.getStage())) {
                dicValueStageList.forEach(dicValueStage -> {
                    Stage stage = new Stage();
                    stage.setId(dicValueStage.getId());
                    stage.setIndex(Integer.parseInt(dicValueStage.getOrderNo()) - 1);
                    stage.setText(dicValueStage.getText());
                    //判断图标类型
                    if (stage.getIndex() < 7) {
                        stage.setType("黑圈");
                    } else if (stage.getIndex() < 8) {
                        stage.setType("黑叉");
                    } else {
                        stage.setType("红叉");
                    }
                    //判断可能性
                    stage.setPossibility(bundle.getString(dicValueStage.getText()));
                    stage.setAnchorPoint(false);
                    stageList.add(stage);
                });
            }
        } else {
            boolean flags = false;
            for (DicValue dicValueStage : dicValueStageList) {
                Stage stage = new Stage();
                stage.setId(dicValueStage.getId());
                stage.setIndex(Integer.parseInt(dicValueStage.getOrderNo()) - 1);
                stage.setText(dicValueStage.getText());
                stage.setAnchorPoint(dicValueStage.getText().equals(tran.getStage()));
                //判断图标类型
                if (stage.getIndex() < 7) {
                    //判断当前图标是否是锚点
                    if (stage.isAnchorPoint()) {
                        stage.setType("锚点");
                        flags = true;
                    } else if (flags) {
                        stage.setType("黑圈");
                    } else {
                        stage.setType("绿圈");
                    }
                } else if (stage.getIndex() < 8) {
                    stage.setType("黑叉");
                } else {
                    stage.setType("黑叉");
                }
                //判断可能性
                stage.setPossibility(bundle.getString(dicValueStage.getText()));
                stage.setAnchorPoint(dicValueStage.getText().equals(tran.getStage()));
                stageList.add(stage);
            }
        }
        return stageList;
    }

    @Override
    public void updateStageList(Tran tran, HttpSession session) throws TranException {
        if (StrUtil.isEmpty(tran.getId()) || StrUtil.isEmpty(tran.getStage())) {
            throw new TranException(TranMessage.TRAN_MESSAGE_PARAMETER_INCOMPLETE);
        }
        ResourceBundle bundle = ResourceBundle.getBundle("base/possibility");
        tran.setPossibility(bundle.getString(tran.getStage()));
        tranMapper.updateByPrimaryKeySelective(tran);

        Tran newTran = tranMapper.selectByPrimaryKey(tran.getId());
        //创建交易历史记录
        TranHistory tranHistory = new TranHistory();
        tranHistory.setId(IdUtil.simpleUUID());
        tranHistory.setTranId(tran.getId());
        tranHistory.setStage(tran.getStage());
        tranHistory.setMoney(newTran.getMoney());
        User user = (User) session.getAttribute("user");
        tranHistory.setCreateBy(user.getId());
        tranHistory.setCreateTime(DateUtil.now());
        tranHistory.setExpectedDate(newTran.getExpectedDate());
        tranHistoryMapper.insertSelective(tranHistory);
    }

    public List<TranHistory> queryTransactionHistoryList(String tranId) throws TranException {
        if (StrUtil.isEmpty(tranId)) {
            throw new TranException(TranMessage.TRAN_MESSAGE_PARAMETER_INCOMPLETE);
        }
        List<TranHistory> tranHistoryList = tranHistoryMapper.selectByTranId(tranId);
        ResourceBundle bundle = ResourceBundle.getBundle("base/possibility");
        for (TranHistory tranHistory : tranHistoryList) {
            String possibility = bundle.getString(tranHistory.getStage());
            tranHistory.setPossibility(possibility);
        }
        return tranHistoryList;
    }
}
