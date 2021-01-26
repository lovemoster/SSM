package cn.syned.crm.settings.controller;

import cn.syned.crm.commons.exception.UserException;
import cn.syned.crm.commons.vo.UserVo;
import cn.syned.crm.settings.entity.User;
import cn.syned.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = {"/", "/index.html", "/index.jsp"})
    public String toLogin() {
        return "forward:/login.html";
    }

    @PostMapping(path = "/user/login")
    @ResponseBody
    public UserVo login(HttpServletRequest request, User user) {
        //获取客户端IP地址并封装到User对象中
        String ip = request.getRemoteAddr();
        user.setAllowips(ip);
        //调用业务层方法判断
        UserVo vo = new UserVo();
        try {
            userService.login(user);
            vo.setCode("001-000");
            vo.setMessage("登录成功");
        } catch (UserException e) {
            vo.setCode(e.getCode());
            vo.setMessage(e.getMessage());
            return vo;
        }
        return vo;
    }
}
