package com.flyingideal.applicationtest.service;

import com.flyingideal.model.User;
import com.flyingideal.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/3/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("admin3");
        user.setPassword("111111");
        int num = userService.addUser(user);
        System.out.println(num);
    }

    @Test
    public void findUserByUsername() {
        String username = "admin";
        User user = userService.getUserByUsername(username);
        System.out.println(user);
    }
}
