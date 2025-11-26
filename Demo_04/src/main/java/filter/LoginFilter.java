package filter;

import entity.User;
import util.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoginFilter初始化");
    }

    //登录过滤器
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //过滤器，从Session中获取用户
        User user = (User) request.getSession().getAttribute(Constants.User_Session);
        //如果用户已登录，直接过滤掉
        if (user != null) {
            filterChain.doFilter(servletRequest, servletResponse);

        } else {
            //需要放行的url地址
            String[] urlArray = new String[]{"login.html", "login", "test.txt"};
            //得到用户请求的URL
            String url = request.getRequestURI();
            //定义是否包含数组地址的boolean型变量
            boolean flag = false;
            //遍历url地址数组
            for (int i = 0; i < urlArray.length; i++) {
                //如果包含在urlArray里，则将flag置为true
                if (url.contains(urlArray[i])) {
                    flag = true;
                    break;
                }
            }

            //如果包含需要放行的url地址，自动过滤
            if (flag) {
                filterChain.doFilter(servletRequest,servletResponse);
            }
            //否则重定向至登录页面
            else {
                response.sendRedirect(request.getContextPath() + "/login.html");
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("LoginFilter销毁");
    }
}