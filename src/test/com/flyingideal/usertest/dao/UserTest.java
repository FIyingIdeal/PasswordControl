package com.flyingideal.usertest.dao;

import com.flyingideal.dao.UserMapper;
import com.flyingideal.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2015/12/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UserTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void UserTest() {
        List<User> allUsers = userMapper.getAllUser();
        for (User user : allUsers) {
            System.out.println(user.getUserName()+ "," +user.getPassword());
        }
    }

    @Test
    public void getCountTest() {
        System.out.println(userMapper.getCount());
    }

    @Test
    public void addUserTest() {
        User user = new User();
        user.setUserName("test");
        user.setPassword("test");
        user.setComment("test");
        int addNum = userMapper.addUser(user);
        System.out.println("成功插入" + addNum + "名用户！");
    }

    @Test
    public void getUserByUserId() {
        User user = userMapper.getUserByUserId(2);
        System.out.println(user.toString());
    }
}
