package cn.syned.crm.settings.controller;

import cn.syned.crm.settings.entity.User;
import cn.syned.crm.settings.service.UserService;
import cn.syned.crm.settings.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(path = "/test")
    @ResponseBody
    public List<User> queryAllUsers(){
        return userService.queryAllUsers();
    }
}
