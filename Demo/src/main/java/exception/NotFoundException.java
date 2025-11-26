package main.java.exception;

/**
 * 资源未找到异常，默认对应 HTTP 404。
 */
public class NotFoundException extends ApiException {

    public NotFoundException(String message) {
        super(404, message);
    }
}

