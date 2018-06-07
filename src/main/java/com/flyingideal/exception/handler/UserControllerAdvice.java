package com.flyingideal.exception.handler;

import com.flyingideal.model.ExceptionResponse;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yanchao
 * @date 2017/9/22 18:17
 */
@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UnknownAccountException.class)
    public ExceptionResponse unknownAccountExceptionHandler(UnknownAccountException e) {
        return new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), "用户名/密码错误");
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ExceptionResponse incorrectCredentialsExceptionHandler(IncorrectCredentialsException e) {
        return new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), "用户名/密码错误");
    }

    @ExceptionHandler(LockedAccountException.class)
    public ExceptionResponse lockedAccountExceptionHandler(LockedAccountException e) {
        return new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), "账号被锁定");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ExceptionResponse authenticationExceptionHandler(AuthenticationException e) {
        return new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), "没有授权的账号");
    }

    @ExceptionHandler(Exception.class)
    public ExceptionResponse getSQLException(Exception exception) {
        String message = "未知错误：" + exception.getMessage();
        return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

}
