//加载booklist页面时自动读取图书列表信息
window.onload = function() {
    //加载页面时将初始化页码和展示的数据条数，并存入缓存
    getBookList(1, 10)
}

//向后端发送请求，分页读取图书列表信息
function getBookList (pageNow, pageSize) {
    $.ajax('http://localhost:8080/getBookList', {
        method: 'GET',
        dataType: "json",
        data: {pageNow: pageNow, pageSize: pageSize},
        success: function (data) {
            console.log(data)
            //若成功获取数据，则使用数据绘制页面
            showBookListAndPageInfo(pageNow, pageSize, data)
        },
        error: function (err) {
            console.log(err)
        }
    })
}

//将数据填充到表格，生成页码信息
function showBookListAndPageInfo (pageNow, pageSize, data) {
    let bookCount = data.content.message.bookCount;
    //清空tbody内容
    $("tbody").html("");
    //将查询得到的图书数据依次写入表格
    $.each(data.content.message.books, function(i, item) {
        let tbody = $("tbody");
        let trTemp = $("<tr></tr>");
        trTemp.append("<td>" + item.id + "</td>");
        trTemp.append("<td>" + item.book_name + "</td>");
        trTemp.append("<td>" + item.author + "</td>");
        trTemp.append("<td>" + formatDate(item.publish_date) + "</td>");
        trTemp.append("<td>" + item.pages + "</td>");
        trTemp.append("<td>" + item.price + "</td>");
        let btnTd = $("<td></td>");
        let btn = $("<button/>", {
            text: "查看详情",
            click: function () {
                showDetails(item.id)
            }
        });
        btnTd.append(btn);
        trTemp.append(btnTd);
        tbody.append(trTemp);
    })
    //页码部分
    let totalPage = Math.ceil(bookCount/pageSize);
    let paginationInfo = getPagination(pageNow, pageSize, totalPage);
    let startPage = (pageNow-1) * pageSize+1;
    let endPage = pageNow * pageSize > bookCount ? bookCount : pageNow*pageSize;
    //用id选择器写入页码
    $("#nav_navigation").html(paginationInfo);
    $("#pageInfo").html("显示第"+startPage+"到第"+endPage+"条数据，总共"+bookCount+"条数据");
}

//构造页码
function getPagination (pageNow, pageSize, totalPage) {
    let paginationInfo = "<ul class='pagination .pagination-sm' >";
    if (pageNow == 1) {
        paginationInfo += "<li class='page-item disabled'><a class='page-link' href='javascript:void(0);'>«</a></li>";
    } else {
        //前一页
        paginationInfo += "<li class='page-item'><a class='page-link' href='javascript:void(0);' onclick='getBookList("+(pageNow-1)+" , "+pageSize + ")'"+">«</a></li>";

    }

    if (totalPage <= 10) {
        //totalPage<=10
        for (let i=1; i<=totalPage; i++) {
            if (i == pageNow) {
                paginationInfo += "<li class='page-item active'> <a class='page-link' href='javascript:void(0);' onclick='getBookList(" + i + " , " + pageSize + " )'>" + i + " </a></li>";
            } else {
                paginationInfo += "<li class='page-item'><a class='page-link' href='javascript:void(0);' onclick='getBookList(" + i + " , " + pageSize + " )'>" + i + " </a></li>";
            }
        }
    } else {
        //totalPage > 10
        if (pageNow <= 3) {
            for (let i=1; i<=pageNow+2; i++) {
                //页码1、2
                if (i == pageNow) {
                    paginationInfo += "<li class='page-item active'> <a class='page-link' href='javascript:void(0);' onclick='getBookList(" + i + " , " + pageSize + ")'>" + i + "</a></li>";
                } else {
                    paginationInfo += "<li class='page-item'> <a class='page-link' href='javascript:void(0);' onclick='getBookList("+ i + " , " + pageSize + ")'>" + i + "</a></li>";
                }
            }
            paginationInfo += "<li class='page-item'><a class='page-link' href='javascript:void(0);'>...</a></li>";
            //最后一页的页码
            paginationInfo += "<li class='page-item'><a class='page-link' href='javascript:void(0);' onclick='getBookList(" + totalPage + " , " + pageSize + ")'>" + totalPage + "</a></li>";
        } else if (pageNow <= totalPage-5) {
            //totalPage > 10   pageNow > 3 pageNow<=totalPage-5，  页码在中间部分
            //页码为1的代码
            paginationInfo += "<li class='page-item'><a class='page-link' href='javascript:void(0);' onclick='getBookList("+1+" , "+ pageSize +")'>" + 1 + "</a></li>";

            //页码1后面的省略号
            paginationInfo += "<li class='page-item'><a class='page-link' href='javascript:void(0);'>...</a></li>";

            //中间部分代码
            for (let i=pageNow-1; i<=pageNow+2; i++) {
                if (i == pageNow) {
                    paginationInfo += "<li class='page-item active'> <a class='page-link' href='javascript:void(0);' onclick='getBookList(" + i + " , " + pageSize + ")'>" + i + "</a></li>";
                } else {
                    paginationInfo += "<li class='page-item'> <a class='page-link' href='javascript:void(0);' onclick='getBookList(" + i + " , " + pageSize + ")'>" + i + "</a></li>";
                }
            }
            //后面的省略号
            paginationInfo += "<li class='page-item'><a class='page-link' href='javascript:void(0);'>...</a></li>";
            //最后一个页码
            paginationInfo += "<li class='page-item'><a class='page-link' href='javascript:void(0);' onclick='getBookList("+totalPage+" , "+pageSize+")'>"+totalPage+"</a></li>";
        } else {
            //totalPage > 10  pageNow > totalPage-5 显示后面的页码
            //页码1
            paginationInfo += "<li class='page-item'><a class='page-link' href='javascript:void(0);' onclick='getBookList("+1+" , "+pageSize+")'>"+1+"</a></li>";
            //省略号
            paginationInfo += "<li class='page-item'><a class='page-link' href='javascript:void(0);'>...</a></li>";
            //最后几位页码
            for (let i=pageNow-1; i<=totalPage; i++) {
                if (i == pageNow) {
                    paginationInfo += "<li class='page-item active'> <a class='page-link' href='javascript:void(0);' onclick='getBookList("+i+" , "+pageSize+")'>"+i+"</a></li>";
                } else {
                    paginationInfo += "<li class='page-item'> <a class='page-link' href='javascript:void(0);' onclick='getBookList("+i+" , "+pageSize+")'>"+i+"</a></li>";
                }
            }
        }
    }

    //下一页
    if (pageNow == totalPage) {
        paginationInfo += "<li class='page-item disabled'> <a class='page-link' href='javascript:void(0);'>»</a></li>";
    } else {
        paginationInfo += "<li class='page-item'><a class='page-link' href='javascript:void(0);' onclick='getBookList("+(pageNow+1)+" , "+pageSize+")'"+">»</a></li>";
    }
    paginationInfo += "</ul>";
    //返回结果
    return paginationInfo;
}

//通过js请求传递参数给图书详情页
function showDetails (id) {
    //点击相应的行的按钮，传递对应id
    window.location.href = "/html/bookdetails.html?id=" + id;
}
