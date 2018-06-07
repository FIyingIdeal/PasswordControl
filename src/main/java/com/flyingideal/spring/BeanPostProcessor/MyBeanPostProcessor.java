package com.flyingideal.spring.BeanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.Objects;

/**
 * @author yanchao
 * @date 2018/4/11 15:19
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (Objects.equals(beanName, "factoryBeanTest"))
            System.out.println(beanName + " MyBeanPostProcessor postProcessBeforeInitialization()方法执行");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (Objects.equals(beanName, "factoryBeanTest"))
            System.out.println(beanName + " MyBeanPostProcessor postProcessAfterInitialization()方法执行");
        return bean;
    }
}
