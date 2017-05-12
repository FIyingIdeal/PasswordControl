package com.flyingideal.controller;

import com.flyingideal.model.User;
import com.flyingideal.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/18.
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/loginValid")
    public Map<String, Object> login(User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        User userInfo = userService.findUserByUsername(user.getUsername());
        userInfo.setPassword(null);
        userInfo.setSalt(null);
        try {
            currentUser.login(token);
            result.put("success", true);
            result.put("message", "登录成功");
            result.put("data", userInfo);
        } catch (UnknownAccountException e) {
            result.put("message", "用户名/密码错误");
        } catch (IncorrectCredentialsException e) {
            result.put("message", "用户名/密码错误");
        } catch (LockedAccountException e) {
            result.put("message", "账号被锁定");
        } catch (AuthenticationException e) {
            result.put("message", "没有授权的账号");
        }
        return result;
    }
}
