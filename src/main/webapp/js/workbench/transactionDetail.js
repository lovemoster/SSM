//定义保存URL查询参数的Map
const queryStringMap = new Map();

//获取URL查询参数
function getQueryString() {
    //获取URL地址
    let url = location.search.substring(1);
    //获取URL地址后面的查询参数，并转换为数组
    let queryString = url.split("&");
    //遍历数组将参数放入Map中
    for (let i = 0; i < queryString.length; i++) {
        queryStringMap.set(queryString[i].split('=')[0], queryString[i].split('=')[1]);
    }
}

//获取Map
getQueryString();

//查询线索列表
function loadTran() {
    $.ajax({
        url: 'queryTransactionById',
        data: {
            id: queryStringMap.get('id')
        },
        method: 'get',
        dataType: 'json',
        success: function (data) {
            // 拼接StageList
            let stageListContent = '阶段&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
            $.each(data.stageList, function (index, item) {

                if (item.type === "黑圈") {
                    stageListContent += '<span id="' + item.id + '" class="glyphicon glyphicon-record mystage" data-toggle="popover" data-placement="bottom"\n' +
                        '          data-content="' + item.text + '"></span>'
                } else if (item.type === "绿圈") {
                    stageListContent += '<span id="' + item.id + '" class="glyphicon glyphicon-record mystage" data-toggle="popover" data-placement="bottom"\n' +
                        '          data-content="' + item.text + '" style="color: #90F790;"></span>'
                } else if (item.type === "锚点") {
                    stageListContent += '<span id="' + item.id + '" class="glyphicon glyphicon-map-marker mystage" data-toggle="popover" data-placement="bottom"\n' +
                        '          data-content="' + item.text + '" style="color: #90F790;"></span>'
                } else if (item.type === "红叉") {
                    stageListContent += '<span id="' + item.id + '" class="glyphicon glyphicon-remove mystage" data-toggle="popover" data-placement="bottom"\n' +
                        '          data-content="' + item.text + '" style="color: red;"></span>'
                } else if (item.type === "黑叉") {
                    stageListContent += '<span id="' + item.id + '" class="glyphicon glyphicon-remove mystage" data-toggle="popover" data-placement="bottom"\n' +
                        '          data-content="' + item.text + '"></span>'
                }
                stageListContent += '-----------';
            })
            stageListContent += data.data.expectedDate;
            $('#stageList').html(stageListContent);
        }
    })
}

loadTran()

function loadTranHistory() {
    $.ajax({
        url: 'queryTransactionHistory',
        data: {
            tranId: queryStringMap.get('id')
        },
        method: 'get',
        dataType: 'json',
        success: function (data) {
            let content = '';
            $.each(data.data, function (index, item) {
                content += '<tr>\n' +
                    '                    <td>' + item.stage + '</td>\n' +
                    '                    <td>' + item.money + '</td>\n' +
                    '                    <td>' + item.possibility + '</td>\n' +
                    '                    <td>' + item.expectedDate + '</td>\n' +
                    '                    <td>' + item.createTime + '</td>\n' +
                    '                    <td>' + item.createBy + '</td>\n' +
                    '                </tr>'
            })
            $('#history-tbody').html(content);
        }
    })
}

loadTranHistory()

$('#stageList').on('mouseover', 'span', function () {
    $(this).popover('show');
}).on('mouseout', 'span', function () {
    $(this).popover('hide');
})


$('#stageList').on('click', 'span', function () {
    //修改StageList
    $.ajax({
        url: 'updateStageList',
        data: {
            id: queryStringMap.get('id'),
            stage: $(this).attr('id'),
        },
        method: 'post',
        dataType: 'json',
        success: function (data) {
            if (data.code === 200) {
                loadTran();
                loadTranHistory();
            }
        }
    })
})

