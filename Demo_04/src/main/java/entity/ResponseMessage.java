package entity;

import java.util.Map;

public class ResponseMessage {
    private String statusCode;
    private String statusMessage;
    private Map<String,Object> content;

    public ResponseMessage() {

    }

    public ResponseMessage(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public ResponseMessage(String statusCode, String statusMessage, Map<String, Object> content) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.content = content;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }
}

