package com.flyingideal.usertest.dao;

import com.flyingideal.dao.UserMapper;
import com.flyingideal.model.UserModel;
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
public class UserModelTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void UserTest() {
        List<UserModel> allUserModels = userMapper.getAllUser();
        for (UserModel userModel : allUserModels) {
            System.out.println(userModel.getUserName()+ "," + userModel.getPassword());
        }
    }

    @Test
    public void getCountTest() {
        System.out.println(userMapper.getCount());
    }

    @Test
    public void addUserTest() {
        UserModel userModel = new UserModel();
        userModel.setUserName("test");
        userModel.setPassword("test");
        userModel.setComment("test");
        int addNum = userMapper.addUser(userModel);
        System.out.println("成功插入" + addNum + "名用户！");
    }

    @Test
    public void getUserByUserId() {
        UserModel userModel = userMapper.getUserByUserId(2);
        System.out.println(userModel.toString());
    }

    @Test
    public void getPasswordTest() {
        String search = "%yan%";
        String orderby = "password";
        List<UserModel> allUserModels = userMapper.getPasswordTest(search,orderby);
        for (UserModel userModel : allUserModels) {
            System.out.println(userModel.getUserName()+ "," + userModel.getPassword());
        }
    }
}
