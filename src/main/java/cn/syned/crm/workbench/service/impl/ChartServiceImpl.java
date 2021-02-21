package cn.syned.crm.workbench.service.impl;

import cn.syned.crm.workbench.entity.Chart;
import cn.syned.crm.workbench.mapper.ChartMapper;
import cn.syned.crm.workbench.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartServiceImpl implements ChartService {

    private ChartMapper chartMapper;

    @Autowired
    public void setChartMapper(ChartMapper chartMapper) {
        this.chartMapper = chartMapper;
    }

    @Override
    public List<Chart> queryTransactionChart() {
        List<Chart> chartList = chartMapper.queryTransactionChart();
        return chartList;
    }
}
