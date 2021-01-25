package cn.syned.crm.settings.entity;

import java.io.Serializable;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User implements Serializable {

    private String id;

    private String loginact;

    private String name;

    private String loginpwd;

    private String email;

    private String expiretime;

    private String lockstate;

    private String deptno;

    private String allowips;

    private String createtime;

    private String createby;

    private String edittime;

    private String editby;

    private static final long serialVersionUID = 1L;
}