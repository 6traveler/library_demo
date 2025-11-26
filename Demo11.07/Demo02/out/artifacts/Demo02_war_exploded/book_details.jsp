<%@page import="edu.cqupt.dao.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书详情</title>
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
        max-width: 800px;
        margin: 0 auto;
        background: white;
        border-radius: 15px;
        box-shadow: 0 10px 40px rgba(0,0,0,0.2);
        padding: 40px;
    }
    h1 {
        color: #333;
        margin-bottom: 30px;
        text-align: center;
        font-size: 32px;
    }
    .book-details {
        background: #f9f9f9;
        border-radius: 10px;
        padding: 30px;
        margin-bottom: 30px;
    }
    .detail-row {
        display: flex;
        padding: 15px 0;
        border-bottom: 1px solid #e0e0e0;
    }
    .detail-row:last-child {
        border-bottom: none;
    }
    .detail-label {
        font-weight: 600;
        color: #555;
        width: 120px;
        flex-shrink: 0;
    }
    .detail-value {
        color: #333;
        flex: 1;
        font-size: 16px;
    }
    .btn-back {
        display: inline-block;
        padding: 12px 24px;
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        color: white;
        text-decoration: none;
        border-radius: 8px;
        transition: all 0.3s;
        font-weight: 600;
    }
    .btn-back:hover {
        transform: translateY(-2px);
        box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
    }
    .btn-container {
        text-align: center;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>📖 图书详情</h1>
        <%
            Book book = (Book)request.getAttribute("bookDetails");
            if (book != null) {
        %>
        <div class="book-details">
            <div class="detail-row">
                <div class="detail-label">图书ID：</div>
                <div class="detail-value"><%= book.getBookId()%></div>
            </div>
            <div class="detail-row">
                <div class="detail-label">图书名：</div>
                <div class="detail-value"><%= book.getBookName()%></div>
            </div>
            <div class="detail-row">
                <div class="detail-label">作者：</div>
                <div class="detail-value"><%= book.getAuthor()%></div>
            </div>
            <div class="detail-row">
                <div class="detail-label">价格：</div>
                <div class="detail-value">￥<%= book.getPrice()%></div>
            </div>
        </div>
        <% } else { %>
        <div style="text-align: center; padding: 40px; color: #999;">
            未找到图书信息
        </div>
        <% } %>
        <div class="btn-container">
            <a href="list_all_book" class="btn-back">返回图书列表</a>
        </div>
    </div>
</body>
</html>