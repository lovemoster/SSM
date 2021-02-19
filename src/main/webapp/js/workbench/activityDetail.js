//定义保存URL查询参数的Map
const queryStringMap = new Map();

//获取URL查询参数
function getQueryString() {
    //获取URL地址
    let url = location.href;
    //获取URL地址后面的查询参数，并转换为数组
    let queryString = url.split('?')[1].split("&");
    //遍历数组将参数放入Map中
    for (let i = 0; i < queryString.length; i++) {
        queryStringMap.set(queryString[i].split('=')[0], queryString[i].split('=')[1]);
    }
}

//获取Map
getQueryString();

//获取当前市场活动的ID
let id = queryStringMap.get('id');

function userInfoLoad() {
    $.ajax({
        url: '../../user/queryAllUsers',
        type: 'get',
        contentType: 'json',
        success: function (data) {
            let content = "";
            $.each(data.data, function (index, item) {
                content += '<option value="' + item.id + '">' + item.name + '</option>';
            })
            $('#edit-marketActivityOwner').html(content);
            loadInfo();
        }
    });
}

//定义加载信息函数
function loadInfo() {

    //通过ID获取此条记录信息
    $.ajax({
        url: 'queryActivity',
        type: 'get',
        data: {
            id
        },
        contentType: 'json',
        success: function (data) {
            if (data.status === 'false') {
                layui.use('layer', function () {
                    let layer = layui.layer;
                    layer.msg('数据加载失败', {icon: 0});
                });
                return false;
            } else if (data.status === 'true') {
                let item = data.aData;
                //全局变量
                activityName = item.name;
                //填充页面数据
                $('#title-name').html('市场活动-' + item.name + ' <small>' + item.startDate + ' ~ ' + item.endDate + '</small>');
                $('#owner-name').text(item.owner);
                $('#name').text(item.name);
                $('#startDate').text(item.startDate);
                $('#endDate').text(item.endDate);
                $('#cost').text(item.cost);
                $('#createBy').html('<b>' + item.createBy + '&nbsp;&nbsp;</b><small style="font-size: 10px; color: gray;">' + item.createTime + '</small>');
                if (undefined !== item.editBy) {
                    $('#editBy').html('<b>' + item.editBy + '&nbsp;&nbsp;</b><small style="font-size: 10px; color: gray;">' + item.editTime + '</small>');
                } else {
                    $('#editBy').html('<b>无</b>');
                }
                $('#description').text(item.description);


                //填充模态框数据
                $('#edit-id').val(item.id);
                $('#edit-marketActivityOwner option').each((index, user) => {
                    console.log(item.owner);
                    if ($(user).text() === item.owner) {
                        $(user).prop('selected', true);
                    }
                });
                $('#edit-marketActivityName').val(item.name);
                $('#edit-startTime').val(item.startDate);
                $('#edit-endTime').val(item.endDate);
                $('#edit-describe').val(item.description);
                $('#edit-cost').val(item.cost);

                //获取市场活动记录
                queryActivityRemark(item.name);

            }
        }
    });
}

//查询市场活动备注
function queryActivityRemark(activityName) {
    $.ajax({
        url: 'queryActivityRemark',
        type: 'get',
        dataType: 'json',
        data: {
            activityId: id
        },
        success: function (data) {
            //清空之前的内容
            $('.remark-content').remove();
            //遍历市场活动记录备注，并渲染页面
            $.each(data.dataList, function (index, item) {
                remarkContent(item, false);
            });
            $("#activity-remark").trigger('mouseover');
        }
    });
}

//渲染市场活动备注
function remarkContent(item, flag) {
    let content = "";
    content += "    <div class=\"remarkDiv remark-content\" style=\"height: 60px;\">\n" +
        "        <img title=\"zhangsan\" src=\"../../image/user-thumbnail.png\" style=\"width: 30px; height:30px;\">\n" +
        "        <div style=\"position: relative; top: -40px; left: 40px;\">\n" +
        "            <h5>" + item.noteContent + "</h5>\n" +
        "            <font color=\"gray\">市场活动</font> <font color=\"gray\">-</font> <b>" + activityName + "</b> <small style=\"color: gray;\">\n" +
        "            " + item.createTime + " 由 " + item.createBy + " 创建</small>\n" +
        "            <div style=\"position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;\">\n" +
        "                <a class=\"myHref\" onclick='editRemark(\"" + item.id + "\")'><span class=\"glyphicon glyphicon-edit\"\n" +
        "                                                                   style=\"font-size: 20px; color: #E6E6E6;\"></span></a>\n" +
        "                &nbsp;&nbsp;&nbsp;&nbsp;\n" +
        "                <a class=\"myHref\" onclick='deleteRemark(\"" + item.id + "\",this)'><span class=\"glyphicon glyphicon-remove\"\n" +
        "                                                                   style=\"font-size: 20px; color: #E6E6E6;\"></span></a>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>";
    if (flag) {
        $('#remarkDiv').before(content);
    } else {
        $('#page-header').after(content);
    }
}

//修改市场活动记录
$('#edit-activity').click(() => {
    $.post('editActivity', $('#editActivity').serialize(), (data) => {
        if (data.status === 'true') {
            layui.use('layer', function () {
                let layer = layui.layer;
                layer.msg('修改成功', {icon: 1});
            });
            loadInfo();
        } else if (data.status === 'false') {
            layui.use('layer', function () {
                let layer = layui.layer;
                layer.msg('修改失败', {icon: 5});
            });
        }
    });
});

//删除市场活动记录
$('#delete-ActivityItem').click(() => {
    layui.use('layer', function () {
        let layer = layui.layer;
        layer.confirm('你确定要删除此记录吗?', {icon: 0, title: '提示'}, function (index) {
            $.ajax({
                url: 'deleteActivity',
                type: 'post',
                dataType: 'json',
                data: {
                    id
                },
                success: function (data) {
                    layui.use('layer', function () {
                        let layer = layui.layer;
                        if (data.status === 'true') {
                            layer.msg('删除成功', {icon: 1});
                            //关闭窗口
                            layer.close(index);
                            //跳转到市场活动主页
                            setTimeout(function () {
                                location.href = 'index.html';
                            }, 1000)
                        } else if (data.status === 'false') {
                            //关闭窗口
                            layer.close(index);
                            layer.msg('删除记录失败', {icon: 5});
                        }
                    });
                }
            });
        });
    });
});

//添加市场活动备注
$('#remark-save').click(() => {

    //获取输入的内容
    let noteContent = $('#remark').val();
    $.ajax({
        url: 'addActivityRemark',
        type: 'post',
        dataType: 'json',
        data: {
            activityId: id,
            noteContent
        },
        success: function (data) {
            layui.use('layer', function () {
                let layer = layui.layer;
                if (data.status === true) {
                    layer.msg('添加成功', {icon: 1});
                    $('#remark').val('');
                    remarkContent(data.data, true);
                } else if (data.status === false) {
                    layer.msg('添加失败', {icon: 5});
                }
            });
        }
    })
});

//修改市场活动备注
function editRemark(id) {
    //发送请求数据
    $.ajax({
        url: 'queryActivityRemarkById',
        type: 'get',
        dataType: 'json',
        data: {
            id: id
        },
        success: function (data) {
            if (data.status === true) {
                layui.use('layer', function () {
                    let layer = layui.layer;
                    if (data.status === true) {
                        $('#noteContent').val(data.data.noteContent);
                        //打开模态窗口
                        $('#editRemarkModal').modal('show');
                    } else if (data.status === false) {
                        layer.msg('查询市场活动备注失败', {icon: 5});
                    }
                });
            }
        }
    });

    $('#updateRemarkBtn').click(function () {
        $.ajax({
            url: 'editActivityRemark',
            type: 'post',
            dataType: 'json',
            data: {
                id: id,
                noteContent: $('#noteContent').val()
            },
            success: function (data) {
                layui.use('layer', function () {
                    let layer = layui.layer;
                    if (data.status === true) {
                        layer.msg('修改成功', {icon: 1});
                        queryActivityRemark(activityName);
                    } else if (data.status === false) {
                        layer.msg('修改失败', {icon: 5});
                    }
                });
            }
        });
        $('#editRemarkModal').modal('hide');
    });

}

//删除市场活动备注
function deleteRemark(id, $this) {
    layui.use('layer', function () {
        let layer = layui.layer;
        layer.confirm('你确定要删除此条记录吗？', {icon: 0}, (index) => {
            $.ajax({
                url: 'deleteActivityRemark',
                type: 'post',
                dataType: 'json',
                data: {
                    id
                },
                success: function (data) {
                    if (data.status === true) {
                        layer.msg('删除成功', {icon: 1});
                        $($this).parent('div').parent('div').parent('div').remove()
                    } else if (data.status === false) {
                        layer.msg('删除失败', {icon: 5});
                    }
                }
            });
            layer.close(index);
        });
    });
}

userInfoLoad();