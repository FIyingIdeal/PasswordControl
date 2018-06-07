package com.flyingideal.applicationtest.service;

import com.flyingideal.model.BlurredPassword;
import com.flyingideal.service.BlurredPasswordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2016/1/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class BlurredPasswordServiceTest {
    @Autowired
    private BlurredPasswordService passwordService;

    @Test
    public void getReletedSubject() {
        String subject = "口琴";
        List<String> list =  passwordService.getReletedSubject(subject);
        System.out.println(list);
    }

    @Test
    public void getBlurredPasswrod() {
        String subject = "蓝调口琴";
        List<BlurredPassword> list = passwordService.getBlurredPassword(subject);
        System.out.println(list);
    }
}
