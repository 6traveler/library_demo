<%@page import="edu.cqupt.dao.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书列表</title>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        min-height: 100vh;
        padding: 20px;
    }
    .container {
        max-width: 1200px;
        margin: 0 auto;
        background: white;
        border-radius: 15px;
        box-shadow: 0 10px 40px rgba(0,0,0,0.2);
        padding: 40px;
    }
    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 30px;
        flex-wrap: wrap;
        gap: 20px;
    }
    h1 {
        color: #333;
        font-size: 32px;
    }
    .btn-add {
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        color: white;
        padding: 12px 24px;
        border: none;
        border-radius: 8px;
        font-size: 16px;
        cursor: pointer;
        text-decoration: none;
        display: inline-block;
        transition: all 0.3s;
        font-weight: 600;
    }
    .btn-add:hover {
        transform: translateY(-2px);
        box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
        box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    }
    thead {
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        color: white;
    }
    th {
        padding: 15px;
        text-align: left;
        font-weight: 600;
    }
    td {
        padding: 15px;
        border-bottom: 1px solid #e0e0e0;
    }
    tbody tr:hover {
        background: #f5f5f5;
        transition: background 0.3s;
    }
    tbody tr:last-child td {
        border-bottom: none;
    }
    .action-buttons {
        display: flex;
        gap: 10px;
        flex-wrap: wrap;
    }
    .btn {
        padding: 8px 16px;
        border: none;
        border-radius: 5px;
        font-size: 14px;
        cursor: pointer;
        text-decoration: none;
        transition: all 0.3s;
        font-weight: 500;
    }
    .btn-view {
        background: #3498db;
        color: white;
    }
    .btn-view:hover {
        background: #2980b9;
        transform: translateY(-1px);
    }
    .btn-edit {
        background: #2ecc71;
        color: white;
    }
    .btn-edit:hover {
        background: #27ae60;
        transform: translateY(-1px);
    }
    .btn-delete {
        background: #e74c3c;
        color: white;
    }
    .btn-delete:hover {
        background: #c0392b;
        transform: translateY(-1px);
    }
    .empty-message {
        text-align: center;
        padding: 40px;
        color: #999;
        font-size: 18px;
    }
    .error {
        color: #e74c3c;
        margin-bottom: 15px;
        padding: 10px;
        background: #ffeaea;
        border-radius: 5px;
        text-align: center;
    }
</style>
<script>
    function confirmDelete(element) {
        var bookId = element.getAttribute('data-book-id');
        var bookName = element.getAttribute('data-book-name');
        if (confirm('确定要删除图书 "' + bookName + '" 吗？')) {
            window.location.href = 'delete_book?book_id=' + bookId;
        }
    }
</script>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>📚 全部图书</h1>
            <a href="add_book" class="btn-add">➕ 添加图书</a>
        </div>
        <% if(request.getAttribute("error") != null) { %>
            <div class="error"><%= request.getAttribute("error") %></div>
        <% } %>
        <%
            List<Book> bookList = (List<Book>)request.getAttribute("bookList");
            if (bookList == null || bookList.isEmpty()) {
        %>
            <div class="empty-message">暂无图书，请添加图书</div>
        <% } else { %>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>图书名</th>
                        <th>作者</th>
                        <th>价格</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Book book : bookList) {
                            // 转义HTML属性值中的特殊字符
                            String bookName = book.getBookName();
                            if (bookName != null) {
                                bookName = bookName.replace("&", "&amp;")
                                                   .replace("\"", "&quot;")
                                                   .replace("'", "&#39;")
                                                   .replace("<", "&lt;")
                                                   .replace(">", "&gt;");
                            }
                    %>
                    <tr>
                        <td><%= book.getBookId()%></td>
                        <td><%= book.getBookName()%></td>
                        <td><%= book.getAuthor()%></td>
                        <td>￥<%= book.getPrice()%></td>
                        <td>
                            <div class="action-buttons">
                                <a href='get_book_details?book_id=<%= book.getBookId()%>' class="btn btn-view">查看详情</a>
                                <a href='get_book_info?book_id=<%= book.getBookId()%>' class="btn btn-edit">修改</a>
                                <a href="javascript:void(0)" onclick="confirmDelete(this)" data-book-id="<%= book.getBookId()%>" data-book-name="<%= bookName != null ? bookName : ""%>" class="btn btn-delete">删除</a>
                            </div>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        <% } %>
    </div>
</body>
</html>