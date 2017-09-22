package com.flyingideal.usertest.dao;

import com.flyingideal.dao.UserMapper;
import com.flyingideal.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2015/12/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UserModelTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void UserTest() {
        List<User> allUserModels = userMapper.getAllUser();
        for (User user : allUserModels) {
            System.out.println(user.getUsername()+ "," + user.getPassword());
        }
    }

    @Test
    public void getCountTest() {
        System.out.println(userMapper.getCount());
    }

    @Test
    public void getUserByUserId() {
        User user = userMapper.getUserByUserId(2);
        System.out.println(user.toString());
    }

    @Test
    public void getRoles() {
        Set<String> roles = userMapper.getRoles("admin");
        System.out.println(roles);
    }

    @Test
    public void getPermissions() {
        Set<String> permissions = userMapper.getPermissions("admin");
        System.out.println(permissions);
    }

}
