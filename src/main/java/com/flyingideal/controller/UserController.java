package com.flyingideal.controller;

import com.flyingideal.model.User;
import com.flyingideal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PutMapping(value = "/regist")
    @ResponseBody
    public Object addUser(@RequestBody User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        if (userService.addUser(user) > 0) {
            result.put("success", true);
        }
        return result;
    }

}
