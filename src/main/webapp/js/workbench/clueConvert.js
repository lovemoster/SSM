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

//查询状态
function loadClueStage() {
    $.ajax({
        url: '../../settings/dictionary/value/queryDictionaryValueByTypeCode',
        data: {
            typeCode: 'stage'
        },
        method: 'get',
        success: function (data) {
            $('#stage').html('');
            let content = '<option value="">请选择</option>';
            $.each(data.data, function (index, item) {
                content += '<option value="' + item.id + '">' + item.text + '</option>'
            });
            $('#stage').html(content);
        }
    })
}

loadClueStage();

//通过线索ID查询线索详情
function loadClue() {
    $.ajax({
        url: 'queryClueById',
        data: {
            id: queryStringMap.get('id')
        },
        method: 'get',
        success: function (data) {
            $('#title').html('<h4>转换线索 <small>' + data.data[0].fullname + data.data[0].appellation + '-' + data.data[0].company + '</small></h4>');
            $('#create-customer').text('新建客户：' + data.data[0].company);
            $('#create-contact').text('新建联系人：' + data.data[0].fullname + data.data[0].appellation);
            $('#owner').html('记录的所有者：<br>\n<b>' + data.data[0].owner + '</b>');
            $('#tradeName').val(data.data[0].company + '-');
        }
    })
}

loadClue();

function loadActivityByName() {
    $.ajax({
        url: '../../workbench/activity/queryActivityByName',
        data: {
            name: $('#search-activity').val()
        },
        method: 'get',
        success: function (data) {
            //判断获得数据是否为0条
            if (data.data.length === 0) {
                $('#activity-tbody').html('');
            } else {
                $('#activity-tbody').html('');
                let content = '';
                $.each(data.data, function (index, item) {
                    content += '<tr>\n' +
                        '                        <td><input type="radio" name="activity" value="' + item.id + ',' + item.name + '"/></td>\n' +
                        '                        <td>' + item.name + '</td>\n' +
                        '                        <td>' + item.startDate + '</td>\n' +
                        '                        <td>' + item.endDate + '</td>\n' +
                        '                        <td>' + item.owner + '</td>\n' +
                        '                    </tr>'
                });
                $('#activity-tbody').html(content);
            }
        }
    })
}

loadActivityByName();

//查询市场活动源
$('#search-activity').bind("input propertychange", function (event) {
    loadActivityByName();
});

//获取市场活动源ID
$('#activity-tbody').on('click', $('input[name=activity]'), function () {
    let value = $('input[name=activity]:checked').val().split(',');
    $('#activity').val(value[1]);
    $('#activity-hidden').val(value[0]);
});

//进行线索转化
$('#toTransaction').click(() => {
    //判断是否需要为用户创建交易
    if ($('#isCreateTransaction').prop('checked')) {
        toTransaction(true);
    } else {
        toTransaction(false);
    }
});

function toTransaction(flag) {
    if (flag) {
        $.ajax({
            url: 'convertClue',
            data: {
                clueId: queryStringMap.get('id'),
                money: $('#amountOfMoney').val(),
                name: $('#tradeName').val(),
                expectedDate: $('#expectedClosingDate').val(),
                stage: $('#stage').val(),
                activityId: $('#activity-hidden').val(),
                flag: flag
            },
            method: 'post',
            success: function (data) {

            }
        })
    } else {
        $.ajax({
            url: 'convertClue',
            data: {
                clueId: queryStringMap.get('id'),
                flag: flag
            },
            method: 'post',
            success: function (data) {

            }
        })
    }
}
