package com.zhang.exception;

/**
 * 自定义异常
 */
public class BaseException extends RuntimeException {

    private int code;

    public BaseException() {
        super();
    }

    public BaseException(String mag) {
        super(mag);
    }

    public BaseException(int code,String mag) {
        super(mag);
        this.code = code;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
