package cn.syned.crm.workbench.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.syned.crm.commons.vo.ClueVo;
import cn.syned.crm.settings.entity.User;
import cn.syned.crm.settings.mapper.UserMapper;
import cn.syned.crm.workbench.entity.Clue;
import cn.syned.crm.workbench.entity.Contacts;
import cn.syned.crm.workbench.entity.Customer;
import cn.syned.crm.workbench.entity.Tran;
import cn.syned.crm.workbench.mapper.ClueMapper;
import cn.syned.crm.workbench.mapper.ContactsMapper;
import cn.syned.crm.workbench.mapper.CustomerMapper;
import cn.syned.crm.workbench.mapper.TranMapper;
import cn.syned.crm.workbench.service.ClueService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClueServiceImpl implements ClueService {

    private ClueMapper clueMapper;
    private UserMapper userMapper;
    private TranMapper tranMapper;
    private ContactsMapper contactsMapper;
    private CustomerMapper customerMapper;

    @Autowired
    public void setClueMapper(ClueMapper clueMapper) {
        this.clueMapper = clueMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setTranMapper(TranMapper tranMapper) {
        this.tranMapper = tranMapper;
    }

    @Autowired
    public void setContactsMapper(ContactsMapper contactsMapper) {
        this.contactsMapper = contactsMapper;
    }

    @Autowired
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    /**
     * 查询所有市场活动线索
     *
     * @param pageNum  当前查询的页数
     * @param pageSize 每页显示的记录数
     * @param clue     线索对象
     * @return 市场活动线索对象
     */
    @Override
    public ClueVo queryClueList(Integer pageNum, Integer pageSize, Clue clue) {
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

        ClueVo clueVo = new ClueVo();
        //如果所有者字段不为空或者null时
        if (StrUtil.isNotEmpty(clue.getOwner())) {
            //根据输入的所有者信息查询对应用户的ID
            List<User> userList = userMapper.selectUserByLikeName(clue.getOwner());
            //如果没有查询到用户信息则返回空，否则根据owner ID查询相应线索记录
            if (userList == null || userList.size() < 1) {
                clueVo.setCode(200);
                clueVo.setMessage("success");
            } else {
                List<String> ownerList = new ArrayList<>();
                userList.forEach(user -> ownerList.add(user.getId()));
                //根据条件查询线索
                List<Clue> clueList = clueMapper.selectClueList(clue, ownerList);
                //封装视图对象
                clueVo.setCode(200);
                clueVo.setMessage("success");
                clueVo.setData(clueList);
            }
            return clueVo;
        }

        //根据条件查询线索
        List<Clue> clueList = clueMapper.selectClueList(clue, null);

        //封装视图对象
        clueVo.setCode(200);
        clueVo.setMessage("success");
        clueVo.setData(clueList);

        return clueVo;
    }

    /**
     * 根据市场线索的ID查询对应线索的详情
     *
     * @param id 线索的UUID
     * @return 市场活动线索对象
     */
    @Override
    public ClueVo queryClueById(String id) {
        ClueVo clueVo = new ClueVo();
        if (StrUtil.isEmpty(id)) {
            clueVo.setCode(401);
            clueVo.setMessage("缺少查询参数");
        } else {
            Clue clue = clueMapper.selectByPrimaryKey(id);
            clueVo.setCode(200);
            clueVo.setMessage("success");
            List<Clue> clueList = new ArrayList<>();
            if (clue != null) {
                clueList.add(clue);
            }
            clueVo.setData(clueList);
        }
        return clueVo;
    }

    /**
     * 转化线索
     *
     * @param tran 要转化的线索对象
     * @param id   线索ID
     * @param flag
     * @return 市场活动线索对象
     */
    @Override
    public ClueVo convertClue(Tran tran, String id, Boolean flag, HttpSession session) {
        //查询此线索的详情
        Clue clue = clueMapper.selectByPrimaryKey(id);

        //获取当前登录用户
        User user = (User) session.getAttribute("user");

        //查询此客户是否存在，如果不存在则添加客户信息
        List<Customer> customerList = customerMapper.selectCustomerByName(clue.getCompany());
        //创建客户
        Customer customer = new Customer();
        if (customerList.size() == 0) {
            //创建客户
            customer.setId(SecureUtil.simpleUUID());
            customer.setOwner(clue.getOwner());
            customer.setName(clue.getCompany());
            customer.setWebsite(clue.getWebsite());
            customer.setPhone(clue.getPhone());
            customer.setCreateBy(user.getId());
            customer.setCreateTime(DateUtil.now());
            customer.setContactSummary(clue.getContactSummary());
            customer.setNextContactTime(clue.getNextContactTime());
            customer.setDescription(clue.getDescription());
            customer.setAddress(clue.getAddress());
            customerMapper.insertSelective(customer);
            //判断客户是否创建成功
        } else {
            customer = customerList.get(0);
        }

        //则添加联系人信息
        Contacts contacts = new Contacts();
        contacts.setId(SecureUtil.simpleUUID());
        contacts.setOwner(clue.getOwner());
        contacts.setSource(clue.getSource());
        contacts.setCustomerId(customer.getId());
        contacts.setFullname(clue.getFullname());
        contacts.setAppellation(clue.getAppellation());
        contacts.setEmail(clue.getEmail());
        contacts.setMphone(clue.getMphone());
        contacts.setJob(clue.getJob());
        contacts.setCreateBy(user.getId());
        contacts.setCreateTime(DateUtil.now());
        contacts.setDescription(clue.getDescription());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setAddress(clue.getAddress());

        contactsMapper.insertSelective(contacts);

        //判断是否为用户创建交易
        if (flag) {
            tran.setId(SecureUtil.simpleUUID());
            tran.setOwner(clue.getOwner());
            tran.setCustomerId(customer.getId());
            tran.setSource(clue.getSource());
            tran.setContactsId(contacts.getId());
            tran.setCreateBy(user.getId());
            tran.setCreateTime(DateUtil.now());
            tran.setDescription(clue.getDescription());
            tran.setContactSummary(clue.getContactSummary());
            tran.setNextContactTime(clue.getNextContactTime());
            int count = tranMapper.insertSelective(tran);
        }

        return null;
    }

}
