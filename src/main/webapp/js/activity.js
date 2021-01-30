let globalPageNum = 1;
let globalPageSize = 10
//初始化页面时获取数据
refresh(globalPageNum, globalPageSize);

$('#search').click(function () {
    refresh(globalPageNum, globalPageSize);
})

//刷新市场活动列表
function refresh(pageNum, pageSize) {
    $.ajax({
        url: 'queryActivityList',
        data: {
            pageNum,
            pageSize,
            name: $('#name').val().trim(),
            owner: $('#owner').val().trim(),
            startDate: $('#startDate').val().trim(),
            endDate: $('#endDate').val().trim()
        },
        contentType: 'json',
        success: function (data) {
            let content = ""
            for (let i = 0; i < data.data.length; i++) {
                let activity = data.data[i];
                content += "<tr class=\"active\">\n" +
                    "\t\t\t\t\t\t\t<td><input type=\"checkbox\" name='xz' value='" + activity.id + "' /></td>\n" +
                    "\t\t\t\t\t\t\t<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href='detail.html';\">" + activity.name + "</a></td>\n" +
                    "                            <td>" + activity.owner + "</td>\n" +
                    "\t\t\t\t\t\t\t<td>" + activity.startDate + "</td>\n" +
                    "\t\t\t\t\t\t\t<td>" + activity.endDate + "</td>\n" +
                    "\t\t\t\t\t\t</tr>";
            }

            $('#activityListBody').html(content);

            let totalPages = Math.ceil(data.totalNum / data.pageSize);
            globalPageSize = data.pageSize;
            globalPageNum = pageNum;

            $("#activityPage").bs_pagination({
                currentPage: pageNum,
                rowsPerPage: data.pageSize,
                maxRowsPerPage: 20,
                totalPages,
                totalRows: 0,
                onChangePage: function (event, obj) {
                    refresh(obj.currentPage, obj.rowsPerPage);
                    $('#search').click(function () {
                        if (obj.rowsPerPage === undefined) {
                            refresh(globalPageNum, globalPageSize);
                        } else {
                            refresh(1, obj.rowsPerPage);
                        }
                    })
                }
            });
        }
    });
}

//添加市场活动
$('#createBtn').click(function () {
    //获取所有者信息
    $.ajax({
        url: '../../user/queryAllUsers',
        type: 'get',
        contentType: 'json',
        success: function (data) {
            let content = "";
            $.each(data.data, function (index, item) {
                content += '<option value="' + item.id + '">' + item.name + '</option>';
            })
            $('#create-marketActivityOwner').html(content);
        }
    });
})

$('#create-save').click(() => {
    $.post('addActivity', $('#addForm').serialize(), function (data) {
        if (data.status === 'true') {
            layui.use('layer', function () {
                let layer = layui.layer;
                layer.msg('添加成功', {icon: 1});
            });
        } else {
            layui.use('layer', function () {
                let layer = layui.layer;
                layer.msg('添加失败', {icon: 5});
            });
        }
        refresh(1, globalPageSize);
        document.getElementById('addForm').reset();
    })
})

//判断全选框是否被选中
$('#qx').click(function () {
    $('input[name=xz]').prop('checked', this.checked);
})

//判断复选框被选中时全选框的状态
$('#activityListBody').on('click', $('#input[name=xz]'), function () {
    $('#qx').prop('checked', $('input[name=xz]').length === $('input[name=xz]:checked').length);
})

//市场活动修改数据展示
$('#edit-editActivityModal').click(() => {
    //判断选中的是否是一条记录
    let checked = $('input[name=xz]:checked');
    if (checked.length === 1) {
        //获取当前选择数据ID
        let id = checked.val();
        //获取所有者信息
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
            }
        });
        //通过ID获取所有此条记录信息
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
                    $('#edit-id').val(item.id);
                    $('#edit-marketActivityOwner option[value="' + item.owner + '"]').prop('selected', true);
                    $('#edit-marketActivityName').val(item.name);
                    $('#edit-startTime').val(item.startDate);
                    $('#edit-endTime').val(item.endDate);
                    $('#edit-describe').val(item.description);
                    $('#edit-cost').val(item.cost);
                    //展示模态窗口
                    $('#editActivityModal').modal('show');
                }
            }
        });
    } else {
        if (checked.length === 0) {
            layui.use('layer', function () {
                let layer = layui.layer;
                layer.msg('请选择一条数据', {icon: 0});
            });
            return false;
        } else if (checked.length > 1) {
            layui.use('layer', function () {
                let layer = layui.layer;
                layer.msg('请只选择一条数据', {icon: 0});
            });
            return false;
        }
    }
})

//修改市场活动记录
$('#edit-activity').click(() => {
    $.post('editActivity', $('#editActivity').serialize(), (data) => {
        if (data.status === 'true') {
            layui.use('layer', function () {
                let layer = layui.layer;
                layer.msg('修改成功', {icon: 1});
            });
            //刷新市场活动列表
            refresh(globalPageNum, globalPageSize);
            //结果集为一条时取消全选框
            $('#qx').prop('checked', false);
        } else if (data.status === 'false') {
            layui.use('layer', function () {
                let layer = layui.layer;
                layer.msg('修改失败', {icon: 5});
            });
        }
    });
})

//删除市场活动记录
$('#delete-ActivityItem').click(function () {
    // 删除按钮被点击时，获取要删除的对象id
    let id = [];
    // 遍历所有对象
    $('input[name=xz]:checked').each(function (index, item) {
        id.push(item.value)
    });
    if (id.length === 0) {
        layui.use('layer', function () {
            let layer = layui.layer;
            layer.msg('请选择要删除的记录', {icon: 5});
        });
        return false;
    }
    if (id.length >= 1) {
        layui.use('layer', function () {
            let layer = layui.layer;
            layer.confirm('你确定要删除选中记录吗?', {icon: 0, title: '提示'}, function (index) {
                $.ajax({
                    url: 'deleteActivity',
                    type: 'post',
                    dataType: 'json',
                    data: {
                        id: id.toString()
                    },
                    success: function (data) {
                        layui.use('layer', function () {
                            let layer = layui.layer;
                            if (data.status === 'true') {
                                layer.msg('删除成功', {icon: 1});
                            } else if (data.status === 'false') {
                                layer.msg('删除记录失败', {icon: 5});
                            }
                        });
                    }
                });
                refresh(globalPageNum, globalPageSize);
                //关闭窗口
                layer.close(index);
            });
        });

    }

})