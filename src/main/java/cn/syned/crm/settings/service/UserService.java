package cn.syned.crm.settings.service;

import cn.syned.crm.commons.exception.UserException;
import cn.syned.crm.settings.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    void login(User user, HttpServletRequest request) throws UserException;

    void modifyUserPassword(String oldPwd, String newPwd, String confirmPwd, HttpSession session) throws UserException;

    List<User> queryAllUsers();
}
