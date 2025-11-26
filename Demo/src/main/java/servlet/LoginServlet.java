package main.java.servlet;

import main.java.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 旧版页面使用 GET，内部继续复用 POST 逻辑
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 读取表单用户名密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User("admin", "123456");

        if ((user.getUsername().equals(username.trim()))
                && (user.getPassword().equals(password.trim()))) {
            resp.sendRedirect("/html/book-list.html");
        } else {
            resp.sendRedirect("/html/book-list.html");
        }
    }
}
