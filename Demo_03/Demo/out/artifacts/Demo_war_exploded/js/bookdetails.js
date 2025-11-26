//读取booklist.js通过地址传递过来的参数
let paValue = [];//创建一个用于保存具体值得数组
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
window.onload=function() {
    //创建XMLHttpRequest对象
    const xhr = new XMLHttpRequest();
    //设置请求方法和url
    xhr.open('POST', 'http://127.0.0.1:8081/getBookDetails');
    //设置请求头
    xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded')
    //发送请求数据
    xhr.send('id='+paValue[0]);
    //监听，处理服务端返回的结果
    xhr.onreadystatechange = function () {
        //判断服务端返回情况
        if (xhr.readyState == 4) {
            //将servlet传递过来的字符串转换成对象
            let resp = JSON.parse(xhr.responseText);
            console.log(resp);
            //如果服务器返回状态码为200，则渲染页面,否则弹窗提示用户
            if(resp.statusCode == 200){
                //根据返回的数据创建表格
                getBookInfo(resp.content.message);
            }else{
                alert(resp.content.message);
                console.log("service error");
            }
        }

    }
}

//处理服务器返回的数据，将图书详情渲染到html页面
function getBookInfo(bookInfo) {
    console.log(bookInfo)
    //操作DOM层添加html内表格数据
    let frontImg = document.querySelector("#frontImg");
    frontImg.src = bookInfo.img;

    let bookNameTd = document.querySelector("#bookName");
    let bookName = document.createTextNode(bookInfo.bookName);
    bookNameTd.appendChild(bookName);

    let authorTd = document.querySelector("#author");
    let author = document.createTextNode(bookInfo.author);
    authorTd.appendChild(author);

    let ISBNTd = document.querySelector("#ISBN");
    let ISBN = document.createTextNode(bookInfo.ISBN);
    ISBNTd.appendChild(ISBN);

    let priceTd = document.querySelector("#price");
    let price = document.createTextNode(bookInfo.price);
    priceTd.appendChild(price);

    let countTd = document.querySelector("#count");
    let count = document.createTextNode(bookInfo.count);
    countTd.appendChild(count);

    let contentTd = document.querySelector("#content");
    let content = document.createTextNode(bookInfo.content);
    contentTd.appendChild(content);
}