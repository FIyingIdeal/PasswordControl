package com.flyingideal.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2017/3/18.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/loginForm")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        if (Objects.equals(UnknownAccountException.class.getName(), exceptionClassName)) {
            result.put("message", "用户名/密码错误");
        } else if (Objects.equals(IncorrectCredentialsException.class.getName(), exceptionClassName)) {
            result.put("message", "用户名/密码错误");
        } else if (exceptionClassName != null) {
            result.put("message", "其他错误");
        } else {
            result.put("success", true);
            result.put("message", "登录成功");
        }
        return result;
        //return "/login.html";
    }
}
