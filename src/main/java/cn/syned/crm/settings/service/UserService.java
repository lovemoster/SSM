package cn.syned.crm.settings.service;

import cn.syned.crm.commons.exception.UserException;
import cn.syned.crm.settings.entity.User;

public interface UserService {

    void login(User user) throws UserException;
}
