package cn.syned.crm.settings.controller;

import cn.syned.crm.commons.exception.UserException;
import cn.syned.crm.commons.message.UserMessage;
import cn.syned.crm.commons.vo.UserVo;
import cn.syned.crm.settings.entity.User;
import cn.syned.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param request
     * @param user
     * @return
     */
    @PostMapping(path = "/user/login")
    @ResponseBody
    public UserVo login(HttpServletRequest request, User user) {
        //获取客户端IP地址并封装到User对象中
        String ip = request.getRemoteAddr();
        user.setAllowips(ip);
        UserVo vo = new UserVo();
        try {
            //调用业务层方法判断
            userService.login(user, request);
            //用户登录成功时返回消息
            vo.setCode(UserMessage.USER_LOGIN_SUCCESS.getCode());
            vo.setMessage(UserMessage.USER_LOGIN_SUCCESS.getMessage());
        } catch (UserException e) {
            //登录抛出异常时返回消息
            vo.setCode(e.getCode());
            vo.setMessage(e.getMessages());
            return vo;
        }
        return vo;
    }

    /**
     * 注销用户登录
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/user/logout")
    public String logout(HttpServletRequest request) {
        request.removeAttribute("user");
        request.getSession().invalidate();
        return "redirect:/";
    }

    /**
     * 修改用户密码
     *
     * @param oldPwd
     * @param newPwd
     * @param confirmPwd
     * @param session
     * @return
     */
    @PostMapping("/user/modifyUserPassword")
    @ResponseBody
    public UserVo modifyUserPassword(@RequestParam(name = "oldPwd") String oldPwd,
                                     @RequestParam(name = "newPwd") String newPwd,
                                     @RequestParam(name = "confirmPwd") String confirmPwd,
                                     HttpSession session) {
        UserVo vo = new UserVo();
        try {
            //调用修改密码方法
            userService.modifyUserPassword(oldPwd, newPwd, confirmPwd, session);
            vo.setCode(UserMessage.USER_UPDATE_PASSWORD_SUCCESS.getCode());
            vo.setMessage(UserMessage.USER_UPDATE_PASSWORD_SUCCESS.getMessage());
        } catch (UserException e) {
            //抛出异常时返回消息
            vo.setCode(e.getCode());
            vo.setMessage(e.getMessages());
            return vo;
        }
        return vo;
    }

    @GetMapping(path = "/user/queryAllUsers")
    @ResponseBody
    public UserVo queryAllUsers() {
        List<User> userList = userService.queryAllUsers();
        UserVo vo = new UserVo();
        vo.setData(userList);
        return vo;
    }
}
