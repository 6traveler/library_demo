package main.java.exception;

/**
 * 自定义运行时异常，携带 HTTP 状态码，方便统一处理错误响应。
 */
public class ApiException extends RuntimeException {

    private final int httpStatus;

    /**
     * @param httpStatus HTTP 状态码
     * @param message    错误描述
     */
    public ApiException(int httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    /**
     * @param httpStatus HTTP 状态码
     * @param message    错误描述
     * @param cause      原始异常
     */
    public ApiException(int httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    /**
     * @return HTTP 状态码，供响应工具使用
     */
    public int getHttpStatus() {
        return httpStatus;
    }
}

