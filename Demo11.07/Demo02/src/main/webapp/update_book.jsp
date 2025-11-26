<%@page import="edu.cqupt.dao.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改图书</title>
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
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 20px;
    }
    .container {
        background: white;
        border-radius: 15px;
        box-shadow: 0 10px 40px rgba(0,0,0,0.2);
        padding: 40px;
        max-width: 500px;
        width: 100%;
    }
    h1 {
        color: #333;
        margin-bottom: 30px;
        text-align: center;
        font-size: 28px;
    }
    .form-group {
        margin-bottom: 20px;
    }
    label {
        display: block;
        margin-bottom: 8px;
        color: #555;
        font-weight: 600;
    }
    input[type="text"] {
        width: 100%;
        padding: 12px;
        border: 2px solid #e0e0e0;
        border-radius: 8px;
        font-size: 16px;
        transition: border-color 0.3s;
    }
    input[type="text"]:focus {
        outline: none;
        border-color: #667eea;
    }
    .button-group {
        display: flex;
        gap: 10px;
        margin-top: 30px;
    }
    .btn {
        flex: 1;
        padding: 12px;
        border: none;
        border-radius: 8px;
        font-size: 16px;
        cursor: pointer;
        transition: all 0.3s;
        font-weight: 600;
        text-align: center;
        text-decoration: none;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .btn-primary {
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        color: white;
    }
    .btn-primary:hover {
        transform: translateY(-2px);
        box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
    }
    .btn-secondary {
        background: #f5f5f5;
        color: #333;
    }
    .btn-secondary:hover {
        background: #e0e0e0;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>✏️ 修改图书</h1>
        <%
            Book book = (Book)request.getAttribute("book");
            if (book != null) {
        %>
        <form action="update_book" method="post">
            <input type='hidden' name='book_id' value='<%= book.getBookId()%>'>
            <div class="form-group">
                <label for="book_name">书名：</label>
                <input type='text' id="book_name" name='book_name' value='<%= book.getBookName()%>' required>
            </div>
            <div class="form-group">
                <label for="author">作者：</label>
                <input type='text' id="author" name='author' value='<%= book.getAuthor()%>' required>
            </div>
            <div class="form-group">
                <label for="price">价格：</label>
                <input type='text' id="price" name='price' value='<%= book.getPrice()%>' required pattern="[0-9]+\.?[0-9]*" title="请输入有效的价格">
            </div>
            <div class="button-group">
                <button type="submit" class="btn btn-primary">提交修改</button>
                <a href="list_all_book" class="btn btn-secondary">返回列表</a>
            </div>
        </form>
        <% } else { %>
        <div style="text-align: center; padding: 40px; color: #999;">
            未找到图书信息
        </div>
        <div class="button-group" style="margin-top: 20px;">
            <a href="list_all_book" class="btn btn-secondary">返回列表</a>
        </div>
        <% } %>
    </div>
</body>
</html>