//查询线索列表
function loadClueList() {
    $.ajax({
        url: 'queryClueList',
        data: {
            fullname: $('#fullname').val(),
            company: $('#company').val(),
            phone: $('#phone').val(),
            source: $('#source').val(),
            owner: $('#owner').val(),
            mphone: $('#mphone').val(),
            state: $('#state').val(),
        },
        method: 'get',
        success: function (data) {
            let content = '';
            $.each(data.data, function (index, item) {
                content += '                <tr>\n' +
                    '                    <td><input type="checkbox"/></td>\n' +
                    '                    <td><a style="text-decoration: none; cursor: pointer;"\n' +
                    '                           onclick="window.location.href=\'detail.html?id=' + item.id + '\';">' + item.fullname + ' ' + item.appellation + '</a></td>\n' +
                    '                    <td>' + item.company + '</td>\n' +
                    '                    <td>' + item.phone + '</td>\n' +
                    '                    <td>' + item.mphone + '</td>\n' +
                    '                    <td>' + item.source + '</td>\n' +
                    '                    <td>' + item.owner + '</td>\n' +
                    '                    <td>' + item.state + '</td>\n' +
                    '                </tr>'
            });
            $('#tbody').html(content);
        }
    })
}

//查询线索来源
function loadClueSource() {
    $.ajax({
        url: '../../settings/dictionary/value/queryDictionaryValueByTypeCode',
        data: {
            typeCode: 'source'
        },
        method: 'get',
        success: function (data) {
            $('#source').html('');
            let content = '<option value="">请选择</option>';
            $.each(data.data, function (index, item) {
                content += '<option value="' + item.id + '">' + item.text + '</option>'
            });
            $('#source').html(content);
        }
    })
}

//查询状态
function loadClueState() {
    $.ajax({
        url: '../../settings/dictionary/value/queryDictionaryValueByTypeCode',
        data: {
            typeCode: 'clueState'
        },
        method: 'get',
        success: function (data) {
            $('#state').html('');
            let content = '<option value="">请选择</option>';
            $.each(data.data, function (index, item) {
                content += '<option value="' + item.id + '">' + item.text + '</option>'
            });
            $('#state').html(content);
        }
    })
}

//进行条件查询时
$('#searchBtn').click(function () {
    loadClueList();
})

loadClueList();
loadClueSource();
loadClueState();