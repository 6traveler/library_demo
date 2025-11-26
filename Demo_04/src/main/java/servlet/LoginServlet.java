package servlet;


import entity.User;
import service.UserService;
import service.Impl.UserServiceImpl;
import util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserService userService = new UserServiceImpl();

        //防止用swagger访问接口时报空指针异常
        if(!username.trim().equals("") && !password.trim().equals("")) {
            User user = userService.login(username, password);
            if (user != null) {
                //将用户的信息存放在Session中
                req.getSession().setAttribute(Constants.User_Session, user);
                req.getRequestDispatcher("/html/booklist.html").forward(req, resp);
                //resp.sendRedirect("/html/booklist.html");
            } else {
                req.getRequestDispatcher("/html/error.html").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/html/error.html").forward(req, resp);
        }

    }
}
