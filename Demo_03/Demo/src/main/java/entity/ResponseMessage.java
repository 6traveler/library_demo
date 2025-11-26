package main.java.entity;

import java.util.Map;

public class ResponseMessage {
    private String statusCode;
    private Map<String,Object> content;

    public ResponseMessage(){

    }

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
