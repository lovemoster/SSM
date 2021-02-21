package cn.syned.crm.workbench.controller;

import cn.syned.crm.commons.vo.ChartVo;
import cn.syned.crm.workbench.entity.Chart;
import cn.syned.crm.workbench.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/workbench/chart")
public class ChartController {

    private ChartService chartService;

    @Autowired
    public void setChartService(ChartService chartService) {
        this.chartService = chartService;
    }

    @GetMapping(path = "/queryTransactionChart")
    @ResponseBody
    public ChartVo<List<Chart>> queryTransactionChart() {
        ChartVo<List<Chart>> chartVo = new ChartVo<>();
        List<Chart> chartList = chartService.queryTransactionChart();
        chartVo.setCode(200);
        chartVo.setMessage("success");
        chartVo.setData(chartList);
        return chartVo;
    }
}
