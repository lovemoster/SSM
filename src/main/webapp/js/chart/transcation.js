let chartDom = document.getElementById('main');
let myChart = echarts.init(chartDom);
let option;

function setOption(data) {
    option = {
        title: {
            text: '交易统计',
            left: 'center',
            textStyle: {
                fontStyle: 'normal',
                fontSize: '32'
            }
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '8%',
            left: 'center'
        },
        series: [
            {
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '32',
                        fontWeight: 'normal'
                    }
                },
                labelLine: {
                    show: false
                },
                data: data
            }
        ]
    };
}

$.ajax({
    url: '../queryTransactionChart',
    method: 'get',
    contentType: 'json',
    success: function (data) {
        setOption(data.data)
        myChart.setOption(option);
    }
})
