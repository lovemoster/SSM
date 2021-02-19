package cn.syned.crm.workbench.controller;

import cn.syned.crm.commons.vo.ActivityRemarkVo;
import cn.syned.crm.workbench.entity.ActivityRemark;
import cn.syned.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/workbench/activity")
public class ActivityRemarkController {

    @Autowired
    private ActivityRemarkService activityRemarkService;

    /**
     * 通过市场活动ID查询此记录的市场活动备注
     *
     * @param activityId 市场活动ID
     */
    @GetMapping(path = "/queryActivityRemark")
    @ResponseBody
    public ActivityRemarkVo queryActivityRemark(@RequestParam(name = "activityId") String activityId) {
        ActivityRemarkVo activityVo = activityRemarkService.queryActivityRemark(activityId);
        return activityVo;
    }

    /**
     * 通过市场活动ID查询此记录的市场活动备注
     *
     * @param id 市场活动备注ID
     */
    @GetMapping(path = "/queryActivityRemarkById")
    @ResponseBody
    public ActivityRemarkVo queryActivityRemarkById(@RequestParam(name = "id") String id) {
        ActivityRemarkVo activityVo = activityRemarkService.queryActivityRemarkById(id);
        return activityVo;
    }

    /**
     * @param activityRemark
     * @param session
     * @return
     */
    @PostMapping(path = "/addActivityRemark")
    @ResponseBody
    public ActivityRemarkVo addActivityRemark(ActivityRemark activityRemark, HttpSession session) {
        ActivityRemarkVo activityVo = activityRemarkService.addActivityRemark(activityRemark, session);
        return activityVo;
    }

    /**
     * 删除市场活动备注
     *
     * @param id
     * @return
     */
    @PostMapping(path = "/deleteActivityRemark")
    @ResponseBody
    public ActivityRemarkVo deleteActivityRemark(@RequestParam(name = "id") String id) {
        ActivityRemarkVo activityRemarkVo = activityRemarkService.deleteActivityRemark(id);
        return activityRemarkVo;
    }

    /**
     * 修改
     *
     * @param activityRemark
     * @return
     */
    @PostMapping(path = "/editActivityRemark")
    @ResponseBody
    public ActivityRemarkVo editActivityRemark(ActivityRemark activityRemark, HttpSession session) {
        ActivityRemarkVo activityVo = activityRemarkService.updateActivityRemark(activityRemark, session);
        return activityVo;
    }
}
