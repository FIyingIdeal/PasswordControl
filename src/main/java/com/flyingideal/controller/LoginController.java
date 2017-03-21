package com.flyingideal.controller;

import com.flyingideal.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    //@RequestMapping(value = "/login")
    //@ResponseBody
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            currentUser.login(token);
        } catch (UnknownAccountException e) {
            result.put("message", "用户名/密码错误");
        } catch (IncorrectCredentialsException e) {
            result.put("message", "用户名/密码错误");
        } catch (LockedAccountException e) {
            result.put("message", "账号被锁定");
        } catch (AuthenticationException e) {
            result.put("message", "没有授权的账号");
        }
        result.put("success", true);
        result.put("message", "登录成功");
        return result;
        //return "/login.html";
    }
}
