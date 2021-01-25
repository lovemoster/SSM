package cn.syned.crm.settings.service.impl;

import cn.syned.crm.settings.entity.User;
import cn.syned.crm.settings.mapper.UserMapper;
import cn.syned.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAllUsers() {
        return userMapper.queryAllUsers();
    }
}
