package cn.syned.crm.workbench.controller;

import cn.syned.crm.commons.vo.ActivityVo;
import cn.syned.crm.workbench.entity.Activity;
import cn.syned.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/workbench")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping(path = "/activity/queryActivityList")
    @ResponseBody
    public ActivityVo queryActivityList(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                        Activity activity) {
        ActivityVo activityVo = activityService.queryActivityList(pageNum, pageSize, activity);
        return activityVo;
    }

    @PostMapping(path = "/activity/addActivity")
    @ResponseBody
    public ActivityVo queryActivityList(Activity activity) {
        int count = activityService.addActivity(activity);
        ActivityVo vo = new ActivityVo();
        if (count != 1) {
            vo.setStatus("false");
        } else {
            vo.setStatus("true");
        }
        return vo;
    }
}
