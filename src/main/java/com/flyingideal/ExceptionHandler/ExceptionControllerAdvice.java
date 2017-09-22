package com.flyingideal.ExceptionHandler;

import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/9.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public Map<String, Object> unauthenticatedExceptionHandler() {
        Map<String, Object> unauthenticatedMessage = new HashMap<String, Object>();
        unauthenticatedMessage.put("success", false);
        unauthenticatedMessage.put("httpStatusCode", 401);
        unauthenticatedMessage.put("message", "用户未登录");
        return unauthenticatedMessage;
    }

    @ExceptionHandler(Exception.class)
    public ExceptionResponse getSQLException(Exception exception) {
        String message = exception.getMessage() + "测试返回错误";
        System.out.println("message 测试返回错误 " + exception);
        return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

}
