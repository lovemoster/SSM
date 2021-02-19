package cn.syned.crm.workbench.controller;

import cn.syned.crm.commons.vo.ActivityVo;
import cn.syned.crm.workbench.entity.Activity;
import cn.syned.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/workbench")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    /**
     * 按条件查询市场活动记录
     *
     * @param activity 查询条件
     * @return 市场活动记录
     */
    @GetMapping(path = "/activity/queryActivityList")
    @ResponseBody
    public ActivityVo queryActivityList(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                        Activity activity) {
        ActivityVo activityVo = activityService.queryActivityList(pageNum, pageSize, activity);
        return activityVo;
    }

    /**
     * 通过市场活动ID进行查询
     *
     * @param id 市场活动记录的ID
     * @return 市场活动记录
     */
    @GetMapping(path = "/activity/queryActivity")
    @ResponseBody
    public ActivityVo queryActivity(@RequestParam("id") String id) {
        ActivityVo vo = new ActivityVo();
        Activity activity = activityService.queryActivity(id);
        if (activity == null) {
            vo.setStatus("false");
        } else {
            vo.setStatus("true");
            vo.setAData(activity);
        }
        return vo;
    }

    /**
     * 添加市场活动记录
     *
     * @param activity 添加的市场活动对象
     * @param session  当前会话对象
     * @return 状态信息
     */
    @PostMapping(path = "/activity/addActivity")
    @ResponseBody
    public ActivityVo queryActivityList(Activity activity, HttpSession session) {
        int count = activityService.addActivity(activity, session);
        ActivityVo vo = new ActivityVo();
        if (count != 1) {
            vo.setStatus("false");
        } else {
            vo.setStatus("true");
        }
        return vo;
    }

    /**
     * 修改市场活动记录
     *
     * @param activity 要修改的市场活动记录对象
     * @param session  当前会话对象
     * @return 状态信息
     */
    @PostMapping(path = "/activity/editActivity")
    @ResponseBody
    public ActivityVo editActivity(Activity activity, HttpSession session) {
        ActivityVo vo = new ActivityVo();
        int count = activityService.editActivity(activity, session);
        if (count != 1) {
            vo.setStatus("false");
        } else {
            vo.setStatus("true");
        }
        return vo;
    }

    /**
     * 删除市场活动记录
     *
     * @param id 要删除条目的UUID
     * @return
     */
    @PostMapping(path = "/activity/deleteActivity")
    @ResponseBody
    public ActivityVo deleteActivity(@RequestParam(name = "id") String id) {
        return activityService.deleteActivity(id);
    }

    /**
     * 根据市场活动名称查询市场活动
     *
     * @param name 市场活动名称
     * @return
     */
    @GetMapping(path = "/activity/queryActivityByName")
    @ResponseBody
    public ActivityVo queryActivityByName(@RequestParam(name = "name") String name) {
        return activityService.queryActivityByName(name);
    }
}
