//初始化页面时获取数据
refresh(1, 10);

$('#search').click(function () {
    refresh(1, 10);
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
                    "\t\t\t\t\t\t\t<td><input type=\"checkbox\" /></td>\n" +
                    "\t\t\t\t\t\t\t<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href='detail.html';\">" + activity.name + "</a></td>\n" +
                    "                            <td>" + activity.owner + "</td>\n" +
                    "\t\t\t\t\t\t\t<td>" + activity.startDate + "</td>\n" +
                    "\t\t\t\t\t\t\t<td>" + activity.endDate + "</td>\n" +
                    "\t\t\t\t\t\t</tr>";
            }

            $('#activityListBody').html(content);

            let totalPages = Math.ceil(data.totalNum / data.pageSize);

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
                            refresh(1, 10);
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
        refresh(1, 10);
        document.getElementById('addForm').reset();
    })
})