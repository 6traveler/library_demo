
//var声明的变量，不是函数作用域就是全局作用域。它们在代码块外也是可见的（不存在块级作用域）—— ES5语法
//let声明的变量只在let命令所在的代码块内有效（块级变量）。 ——ES6语法
//const声明一个只读的常量，一旦声明，常量的值就不能改变。  ——ES6语法

//加载booklist页面时自动读取图书列表信息
window.onload = function() {
    //创建XMLHttpRequest对象
    const xhr = new XMLHttpRequest();
    //设置请求方法和url
    xhr.open('GET', 'http://127.0.0.1:8081/getBookList');
    //发送
    xhr.send();
    //监听，处理服务端返回的结果
    xhr.onreadystatechange = function () {
        //判断服务端返回情况
        if (xhr.readyState == 4) {
            //将servlet传递过来的字符串转换成对象
            let resp = JSON.parse(xhr.responseText);
            //如果服务器返回状态码为200，则渲染页面,否则弹窗提示用户
            if (resp.statusCode == 200) {
                //根据返回的数据创建表格
                console.log(resp.content.message);
                createTable(resp.content.message);
            } else {
                alert(resp.content.message);
                console.log("service error");
            }
        }
    }
}

//读取服务器返回数据并渲染到html页面
function createTable(response) {
    let bookList = response;
    let tbody = document.getElementById("tbody");

    for(let key in bookList) {
        //循环插入所有的行到tbody中
        tbody.insertRow(key)
        for (let attribute in bookList[key]) {
            //在tbody中的行中插入一个单元格
            let td_node = tbody.rows[key].insertCell(-1);
            //创建一个文本节点
            let text_node = document.createTextNode(bookList[key][attribute]);
            td_node.appendChild(text_node);
        }

        let td_node = tbody.rows[key].insertCell(-1);
        //createElement生成button对象
        let btn_node = document.createElement("button");
        btn_node.innerHTML = '查看详情';
        //为按钮绑定点击事件，点击按钮的时候调用函数获取图书详情
        btn_node.onclick = function () {
            showDetails(this.parentNode.parentNode);
        }
        td_node.appendChild(btn_node);
    }
}

//通过js请求传递参数给图书详情页
function showDetails(obj) {
    let id = obj.childNodes[0].innerText;
    //点击相应的行的按钮，传递对应id
    window.location.href = "/html/bookdetails.html?id="+id;
}