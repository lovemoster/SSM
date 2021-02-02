package cn.syned.crm.commons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class ViewController {

    //======================================Workbench=======================================

//    @GetMapping(path = {"/workbench/{view}", "/workbench/", "/workbench/index.html"})
//    public String toWorkBenchView(
//            @PathVariable(value = "view", required = false) String view) {
//        if (view == null) {
//            return "/WEB-INF/workbench/index.jsp";
//        }
//        return "/WEB-INF/workbench" + File.separator + view;
//    }
//
//    @GetMapping(path = "/workbench/{module}/{view}")
//    public String toWorkBenchSubView(
//            @PathVariable("module") String module,
//            @PathVariable("view") String view) {
//        return "/WEB-INF/workbench" + File.separator + module + File.separator + view;
//    }

    //======================================Settings=======================================

//    @GetMapping(path = {"/settings/{view}", "/settings/"})
//    public String toSettingsView(
//            @PathVariable(value = "view", required = false) String view) {
//        if (view == null) {
//            return "/WEB-INF/settings/index.html";
//        }
//        return "/WEB-INF/settings" + File.separator + view;
//    }
//
//    @GetMapping(path = "/settings/{module}/{view}")
//    public String toSettingsModuleView(
//            @PathVariable("module") String module,
//            @PathVariable(value = "view") String view) {
//        return "/WEB-INF/settings" + File.separator + module + File.separator + view;
//    }
//
//    @GetMapping(path = "/settings/{module}/{submodule}/{view}")
//    public String toSettingsSubModuleView(
//            @PathVariable("module") String module,
//            @PathVariable("submodule") String submodule,
//            @PathVariable(value = "view") String view) {
//        return "/WEB-INF/settings" + File.separator + module + File.separator + submodule + File.separator + view;
//    }

    @GetMapping(path = "/settings/**")
    public String toSettings(HttpServletRequest request) {
        String path = request.getServletPath();
        return "/WEB-INF" + File.separator + path;
    }

    @GetMapping(path = "/workbench/**")
    public String toWorkBench(HttpServletRequest request) {
        String path = request.getServletPath();
        return "/WEB-INF" + File.separator + path;
    }
}
