package cn.syned.crm.commons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;

@Controller
public class ViewController {
    @GetMapping(path = {"/workbench/{view}", "/workbench/"})
    public String toWorkBenchView(
            @PathVariable(value = "view", required = false) String view) {
        if (view == null) {
            return "/WEB-INF/workbench/index.html";
        }
        return "/WEB-INF/workbench" + File.separator + view;
    }

    @GetMapping(path = "/workbench/{module}/{view}")
    public String toWorkBenchSubView(
            @PathVariable("module") String module,
            @PathVariable("view") String view) {
        return "/WEB-INF/workbench" + File.separator + module + File.separator + view;
    }

    @GetMapping(path = {"/settings/{view}", "/settings/"})
    public String toSettingsView(
            @PathVariable(value = "view", required = false) String view) {
        if (view == null) {
            return "/WEB-INF/settings/index.html";
        }
        return "/WEB-INF/settings" + File.separator + view;
    }

    @GetMapping(path = "/settings/{module}/{view}")
    public String toSettingsSubView(
            @PathVariable("module") String module,
            @PathVariable(value = "view") String view) {
        return "/WEB-INF/settings" + File.separator + module + File.separator + view;
    }

    @GetMapping(path = "/settings/dictionary/{module}/{view}")
    public String toSettingsSubsView(
            @PathVariable("module") String module,
            @PathVariable(value = "view") String view) {
        return "/WEB-INF/settings/dictionary" + File.separator + module + File.separator + view;
    }
}
