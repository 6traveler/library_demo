package main.java.util;

import com.google.gson.Gson;
import main.java.entity.ResponseMessage;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一的响应输出工具，确保 JSON 结构与 SPA 约定一致。
 */
public final class ResponseWriter {

    private static final Gson GSON = new Gson();

    private ResponseWriter() {
    }

    /**
     * 发送 200 OK 的成功响应。
     */
    public static void writeSuccess(HttpServletResponse resp, Object payload) throws IOException {
        write(resp, HttpServletResponse.SC_OK, "200", payload);
    }

    /**
     * 发送指定 HTTP 状态码的成功响应（如 201 Created）。
     */
    public static void writeSuccess(HttpServletResponse resp, int httpStatus, Object payload) throws IOException {
        String statusCode = httpStatus >= 200 && httpStatus < 300 ? "200" : String.valueOf(httpStatus);
        write(resp, httpStatus, statusCode, payload);
    }

    /**
     * 发送错误响应，消息体中为可读提示。
     */
    public static void writeError(HttpServletResponse resp, int httpStatus, String message) throws IOException {
        write(resp, httpStatus, String.valueOf(httpStatus), message);
    }

    /**
     * 底层写入方法，负责设置编码、状态码并序列化 JSON。
     */
    private static void write(HttpServletResponse resp, int httpStatus, String statusCode, Object payload) throws IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json;charset=UTF-8");
        resp.setStatus(httpStatus);

        Map<String, Object> content = new HashMap<>();
        content.put("message", payload);
        ResponseMessage responseMessage = new ResponseMessage(statusCode, content);
        resp.getWriter().write(GSON.toJson(responseMessage));
    }
}
