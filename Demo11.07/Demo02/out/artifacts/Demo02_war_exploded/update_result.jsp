<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新结果</title>
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
        text-align: center;
    }
    h1 {
        color: #333;
        margin-bottom: 30px;
        font-size: 28px;
    }
    .status-message {
        font-size: 18px;
        margin-bottom: 30px;
        padding: 20px;
        border-radius: 8px;
    }
    .status-success {
        background: #d4edda;
        color: #155724;
        border: 1px solid #c3e6cb;
    }
    .status-error {
        background: #f8d7da;
        color: #721c24;
        border: 1px solid #f5c6cb;
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
</style>
</head>
<body>
    <div class="container">
        <h1>更新结果</h1>
        <%
            String status = (String)request.getAttribute("status");
            boolean isSuccess = status != null && status.contains("成功");
        %>
        <div class="status-message <%= isSuccess ? "status-success" : "status-error" %>">
            <%= status != null ? status : "未知状态" %>
        </div>
        <a href="list_all_book" class="btn-back">返回图书列表</a>
    </div>
</body>
</html>