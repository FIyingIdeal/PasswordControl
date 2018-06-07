package com.flyingideal.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yanchao
 * @date 2018/4/11 15:29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class MyBeanFactoryPostProcessorTest {

    @Autowired
    private com.flyingideal.model.BeanFactoryPostProcessorTestBean beanFactoryPostProcessorTestBean;

    @Test
    public void beanFactoryPostProcessorTest() {
        System.out.println(beanFactoryPostProcessorTestBean);

    }
}
