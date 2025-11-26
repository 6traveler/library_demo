//读取booklist.js通过地址传递过来的参数
const paValue = [];//创建一个用于保存具体值得数组
let loc = location.href;//获取整个跳转地址内容，其实就是你传过来的整个地址字符串
let n1 = loc.length;//地址的总长
let n2 = loc.indexOf("?");//取得=号的位置
let parameter = decodeURI(loc.substr(n2+1, n1-n2));//截取从?号后面的内容,也就是参数列表，因为传过来的路径是加了码的，所以要解码
let parameters  = parameter.split("&");//从&处拆分，返回字符串数组
for (let i = 0; i < parameters.length; i++) {
    let m1 = parameters[i].length;//获得每个键值对的长度
    let m2 = parameters[i].indexOf("=");//获得每个键值对=号的位置
    paValue[i] = parameters[i].substr(m2+1, m1-m2);//获取每个键值对=号后面具体的值
}


//加载bookdetails页面时自动获取图书详情信息
window.onload = function() {
    $.ajax({
        url: 'http://localhost:8080/getBookDetails', //url地址
        method: 'post', //请求方式
        data: { id: paValue[0] }, //传入的数据
        dataType: 'json', //把json字符串自动解析成正确的数据（替代JSON.parse）
        success: function (res) {
            console.log(res);
            //判定返回状态码是否是200，不是则提示错误信息
            if (res.statusCode == 200) {
                console.log(res.content)
                getBookInfo(res.content.message);
            } else {
                alert(res.statusMessage);
            }
        },
        error: function (err) {
            console.log(err);
        }
    })
}

//处理服务器返回的数据，将图书详情渲染到html页面
function getBookInfo (bookInfo) {
    //对img添加src属性
    $('#frontImg').attr('src', bookInfo.picture);

    //对td标签后添加新的td标签
    $('#bookName').append("<td>" + bookInfo.book_name + "</td>");
    $('#author').append("<td>" + bookInfo.author + "</td>");

    //时间戳转换成标准格式
    let publish_date = formatDate(bookInfo.publish_date);
    $('#publish_date').append("<td>" + publish_date + "</td>");

    $('#pages').append("<td>" + bookInfo.pages + "</td>");
    $('#price').append("<td>" + bookInfo.price + "</td>");
    $('#content').append("<td>" + bookInfo.content + "</td>");
}