package cn.syned.crm.settings.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.syned.crm.commons.exception.UserException;
import cn.syned.crm.commons.message.UserMessage;
import cn.syned.crm.settings.entity.User;
import cn.syned.crm.settings.mapper.UserMapper;
import cn.syned.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void login(User user) throws UserException {
        //判断输入的用户名密码是否为空
        if (StrUtil.isEmpty(user.getLoginact())) {
            //如果用户名为空则抛出异常
            throw new UserException(UserMessage.USER_LOGIN_USERNAME_EMPTY);
        } else if (StrUtil.isEmpty(user.getLoginpwd())) {
            //如果密码为空则抛出异常
            throw new UserException(UserMessage.USER_LOGIN_PASSWORD_EMPTY);
        }

        //对密码进行MD5加密
        user.setLoginpwd(SecureUtil.md5(user.getLoginpwd()));

        //查询数据库比对用户名密码
        User res = userMapper.login(user);
        //判断用户名密码是否正确
        if (res == null) {
            //抛出用户名或密码错误
            throw new UserException(UserMessage.USER_LOGIN_USERNAME_OR_PASSWORD_INCORRECT);
        }
        //判断用户账户是否过期
        String expireTime = res.getExpiretime();
        String currentTime = DateUtil.formatDateTime(new Date());
        if (currentTime.compareTo(expireTime) > 0) {
            throw new UserException(UserMessage.USER_LOGIN_ACCOUNT_OVERDUE);
        }
        //判断用户账户是否被锁定
        if ("0".equals(res.getLockstate())) {
            throw new UserException(UserMessage.USER_LOGIN_ACCOUNT_LOCKED);
        }
        //判断IP地址是否被禁止访问
        if (!res.getAllowips().contains(user.getAllowips())) {
            throw new UserException(UserMessage.USER_LOGIN_IP_BLOCKED);
        }

    }
}
