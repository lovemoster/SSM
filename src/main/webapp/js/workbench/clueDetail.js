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

function toConvert() {
    location.href = 'convert.html?id=' + queryStringMap.get('id');
}