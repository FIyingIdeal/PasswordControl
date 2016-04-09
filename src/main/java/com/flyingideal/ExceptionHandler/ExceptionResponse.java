package com.flyingideal.ExceptionHandler;

/**
 * Created by Administrator on 2016/4/9.
 * 异常信息包装类
 */
public class ExceptionResponse {
    private int code;
    private String message;

    public ExceptionResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ExceptionResponse create(int code, String message) {
        return new ExceptionResponse(code, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
