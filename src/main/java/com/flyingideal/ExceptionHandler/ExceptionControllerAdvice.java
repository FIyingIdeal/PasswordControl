package com.flyingideal.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/4/9.
 */
@ControllerAdvice(annotations = RestController.class)
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ExceptionResponse getSQLException(Exception exception) {
        String message = exception.getMessage() + "测试返回错误";
        System.out.println("message 测试返回错误");
        return ExceptionResponse.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }
}
