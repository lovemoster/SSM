//查询线索列表
function loadTranList() {
    $.ajax({
        url: 'queryTransactionList',
        data: {
            owner: $('#owner').val(),
            name: $('#').val(),
            customerId: $('#customerId').val(),
            source: $('#source').val(),
            type: $('#TransactionType').val(),
            stage: $('#stage').val(),
            contactsId: $('#contactsId').val(),
        },
        method: 'get',
        success: function (data) {
            let content = '';
            $.each(data.data, function (index, item) {
                content += '                <tr>\n' +
                    '                    <td><input type="checkbox"/></td>\n' +
                    '                    <td><a style="text-decoration: none; cursor: pointer;"\n' +
                    '                           onclick="window.location.href=\'detail.html?id=' + item.id + '\';">' + item.name + '</a></td>\n' +
                    '                    <td>' + item.customerId + '</td>\n' +
                    '                    <td>' + item.stage + '</td>\n' +
                    '                    <td>' + item.type + '</td>\n' +
                    '                    <td>' + item.owner + '</td>\n' +
                    '                    <td>' + item.source + '</td>\n' +
                    '                    <td>' + item.contactsId + '</td>\n' +
                    '                </tr>'
            });
            $('#tbody').html(content);
        }
    })
}

//查询阶段
function loadStage() {
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

//查询交易类型
function loadTransactionType() {
    $.ajax({
        url: '../../settings/dictionary/value/queryDictionaryValueByTypeCode',
        data: {
            typeCode: 'transactionType'
        },
        method: 'get',
        success: function (data) {
            $('#TransactionType').html('');
            let content = '<option value="">请选择</option>';
            $.each(data.data, function (index, item) {
                content += '<option value="' + item.id + '">' + item.text + '</option>'
            });
            $('#TransactionType').html(content);
        }
    })
}

//查询来源
function loadSource() {
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

//进行条件查询时
$('#searchBtn').click(function () {
    loadTranList();
})

loadTranList();
loadStage();
loadTransactionType();
loadSource();