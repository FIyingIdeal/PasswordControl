package com.flyingideal.spring;

import com.flyingideal.spring.ioc.IocTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yanchao
 * @date 2018/4/11 17:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class IOCTest {

    @Autowired
    private IocTest iocTest;

    @Test
    public void arrayClassInject() {
        System.out.println(iocTest);
    }
}
