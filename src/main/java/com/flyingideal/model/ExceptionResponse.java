package com.flyingideal.model;

/**
 * Created by Administrator on 2016/4/9.
 * 异常信息包装类
 */
public class ExceptionResponse {

    private boolean success;
    private int httpStatusCode;
    private String message;

    public ExceptionResponse(boolean success, int httpStatusCode, String message) {
        this.success = success;
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    public ExceptionResponse(int httpStatusCode, String message) {
        success = false;
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
