package cn.syned.crm.commons.vo;

import cn.syned.crm.settings.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserVo {
    private String code;
    private String message;
    private List<User> data;
}
