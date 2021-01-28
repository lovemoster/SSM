package cn.syned.crm.commons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping(path = {"/", "/index.html", "/index.jsp"})
    public String toLogin() {
        return "forward:/login.html";
    }
}
