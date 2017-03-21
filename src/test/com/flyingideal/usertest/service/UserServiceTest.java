package com.flyingideal.usertest.service;

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
        user.setUsername("admin1");
        user.setPassword("123456");
        int num = userService.addUser(user);
        System.out.println(num);
    }
}
