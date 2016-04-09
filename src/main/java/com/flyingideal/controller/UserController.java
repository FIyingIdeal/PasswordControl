package com.flyingideal.controller;

import com.flyingideal.model.UserModel;
import com.flyingideal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/3.
 */
@Controller
@EnableWebMvcSecurity
@RequestMapping(value = "/user")
public class UserController {

    private static final String CURRENTUSER = "currentUser";
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/adduser", method = RequestMethod.PUT)
    @ResponseBody
    public Object addUser(@RequestBody UserModel userModel) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", false);
        if (userService.addUser(userModel) > 0) {
            result.put("status", true);
        }
        return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@AuthenticationPrincipal User user, @RequestBody UserModel userModel, HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", true);
        System.out.println("111111111111111111" + user);
        System.out.println(user.getUsername() + "    " + user.getPassword());
        UserModel dbUser = userService.login(userModel);
        if (dbUser != null) {
            session.setAttribute(CURRENTUSER, dbUser);
            result.put("userName", dbUser.getUserName());
        }
        return result;
    }
}
