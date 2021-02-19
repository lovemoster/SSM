package cn.syned.crm.workbench.controller;

import cn.syned.crm.commons.vo.ClueVo;
import cn.syned.crm.workbench.entity.Clue;
import cn.syned.crm.workbench.entity.Tran;
import cn.syned.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/workbench/clue")
public class ClueController {

    private ClueService clueService;

    @Autowired
    public void setClueService(ClueService clueService) {
        this.clueService = clueService;
    }

    @GetMapping(path = "/queryClueList")
    @ResponseBody
    public ClueVo queryClueList(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                                @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                Clue clue) {
        ClueVo clueVo = clueService.queryClueList(pageNum, pageSize, clue);
        return clueVo;
    }

    @GetMapping(path = "/queryClueById")
    @ResponseBody
    public ClueVo queryClueById(@RequestParam(value = "id") String id) {
        ClueVo clueVo = clueService.queryClueById(id);
        return clueVo;
    }

    @PostMapping(path = "/convertClue")
    @ResponseBody
    public ClueVo convertClue(@RequestParam(name = "clueId") String id,
                              @RequestParam(name = "flag") Boolean flag,
                              HttpSession session,
                              Tran tran) {
        ClueVo clueVo = clueService.convertClue(tran, id, flag, session);
        return clueVo;
    }
}
