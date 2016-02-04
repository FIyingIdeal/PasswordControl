package com.flyingideal.controller;

import com.flyingideal.model.User;
import com.flyingideal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/user")
public class UserController {

    private static final String CURRENTUSER = "currentUser";
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/adduser", method = RequestMethod.PUT)
    @ResponseBody
    public Object addUser(@RequestBody User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", false);
        if (userService.addUser(user) > 0) {
            result.put("status", true);
        }
        return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody User user, HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", true);
        User dbUser = userService.login(user);
        if (dbUser != null) {
            session.setAttribute(CURRENTUSER, dbUser);
            result.put("userName", dbUser.getUserName());
        }
        return result;
    }
}
