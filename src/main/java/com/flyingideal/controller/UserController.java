package com.flyingideal.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.flyingideal.jsonview.UserJsonView;
import com.flyingideal.model.User;
import com.flyingideal.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/3.
 */
@Api(value = "User控制器")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录", httpMethod = "POST", produces = "application/json")
    @ApiResponse(code = 200, message = "login success", response = User.class)
    @PostMapping(value = "/login")
    @JsonView(UserJsonView.class)
    public Map<String, Object> login(User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            currentUser.login(token);
            User userInfo = userService.getUserByUsername(user.getUsername());
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
            e.printStackTrace();
            result.put("message", "没有授权的账号");
        }
        return result;
    }

    @ApiOperation(value = "用户注册", httpMethod = "POST", produces = "application/json")
    @PostMapping(value = "/regist")
    public Object addUser(@RequestBody User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        result.put("message", "用户名已存在");
        if (checkUsername(user.getUsername())
                && userService.addUser(user) > 0) {
            result.put("success", true);
            result.put("message", "用户注册成功");
        }
        return result;
    }

    private boolean checkUsername(String username) {
        User user = userService.getUserPublicInfoByUsername(username);
        return user == null ? true : false;
    }

}
