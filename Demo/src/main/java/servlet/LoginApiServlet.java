package main.java.servlet;

import com.google.gson.Gson;
import main.java.entity.User;
import main.java.util.ResponseWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * SPA 登录接口，返回伪 token，后续可替换为真实鉴权实现。
 */
public class LoginApiServlet extends HttpServlet {

    private final Gson gson = new Gson();
    private transient User defaultUser;

    @Override
    public void init() throws ServletException {
        super.init();
        // Demo 账号，可改为数据库查询
        defaultUser = new User("admin", "123456");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) {
        setCorsHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    /**
     * POST /api/login，校验用户名密码并返回伪 token。
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setCorsHeaders(resp);
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (BufferedReader reader = req.getReader()) {
            LoginPayload payload = gson.fromJson(reader, LoginPayload.class);
            if (payload == null || payload.username == null || payload.password == null) {
                ResponseWriter.writeError(resp, 400, "用户名或密码不能为空");
                return;
            }
            if (defaultUser.getUsername().equals(payload.username.trim())
                    && defaultUser.getPassword().equals(payload.password.trim())) {
                boolean rememberEnabled = payload.rememberMe != null && payload.rememberMe;
                Map<String, Object> message = new HashMap<>();
                message.put("token", UUID.randomUUID().toString());
                message.put("username", defaultUser.getUsername());
                message.put("rememberMe", rememberEnabled);
                ResponseWriter.writeSuccess(resp, message);
            } else {
                ResponseWriter.writeError(resp, 401, "用户名或密码错误");
            }
        }
    }

    /**
     * 设置跨域响应头。
     */
    private void setCorsHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST,OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }

    private static class LoginPayload {
        String username;
        String password;
        Boolean rememberMe;
    }
}

