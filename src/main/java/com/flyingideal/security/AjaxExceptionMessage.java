package com.flyingideal.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flyingideal.utility.BeanJsonConverter;

import java.io.Serializable;

/**
 * @author yanchao
 * @date 2017/9/21 16:20
 */
public class AjaxExceptionMessage implements Serializable {

    private boolean success;
    private int httpStatusCode;
    private String message;

    public AjaxExceptionMessage(boolean success, int httpStatusCode, String message) {
        this.success = success;
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

    public String toJson() throws JsonProcessingException{
        return BeanJsonConverter.bean2Json(this);
    }

    @Override
    public String toString() {
        return "AjaxExceptionMessage{" +
                "success=" + success +
                ", httpStatusCode=" + httpStatusCode +
                ", message='" + message + '\'' +
                '}';
    }
}
