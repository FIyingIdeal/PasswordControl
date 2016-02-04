package com.flyingideal.usertest.dao;

import com.flyingideal.dao.BlurredPasswordMapper;
import com.flyingideal.model.BlurredPassword;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2015/12/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class BlurredPasswordTest {
    @Autowired
    private BlurredPasswordMapper passwordMapper;

    @Test
    public void addPasswordTest() {
        BlurredPassword password = new BlurredPassword();
        password.setSubject("支付宝");
        password.setPassword("y****(true birthday)***");
        password.setDescription("test");
        int addNum = passwordMapper.addBlurredPassword(password);
        System.out.println(addNum);
    }
}
