package main.java.entity;

import java.util.Map;

/**
 * 统一的响应封装，方便前端读取 {statusCode, content} 结构。
 */
public class ResponseMessage {
    private String statusCode;
    private Map<String,Object> content;

    /**
     * 默认构造器，供 JSON 反序列化使用。
     */
    public ResponseMessage(){

    }

    /**
     * @param statusCode 业务状态码
     * @param content    具体消息体
     */
    public ResponseMessage(String statusCode, Map<String, Object> content) {
        this.statusCode = statusCode;
        this.content = content;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }
}
