package com.flyingideal.spring.BeanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author yanchao
 * @date 2018/4/11 15:02
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor postProcessBeanFactory()方法执行！");
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("factoryBeanTest");
        MutablePropertyValues values = beanDefinition.getPropertyValues();
        if (values.contains("name")) {
            values.addPropertyValue("name", "BeanFactoryPostProcessor name");
        }
    }
}
