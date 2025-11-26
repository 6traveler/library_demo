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
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User("admin", "123456");

        if ((user.getUsername().equals(username.trim()))
                && (user.getPassword().equals(password.trim()))) {
            req.getRequestDispatcher("/html/booklist.html").forward(req,resp);
            //resp.sendRedirect("/html/booklist.html");
        } else {
            resp.sendRedirect("/html/error.html");
        }
    }
}
