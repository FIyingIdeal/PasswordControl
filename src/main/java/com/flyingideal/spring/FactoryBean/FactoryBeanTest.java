package com.flyingideal.spring.FactoryBean;

import com.flyingideal.model.BeanFactoryPostProcessorTestBean;
import com.flyingideal.model.User;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by Administrator on 2017/6/30.
 */
public class FactoryBeanTest implements FactoryBean<BeanFactoryPostProcessorTestBean>, InitializingBean {

    private BeanFactoryPostProcessorTestBean bean;

    public FactoryBeanTest() {
        System.out.println("FactoryBeanTest默认构造方法");
    }

    public FactoryBeanTest(BeanFactoryPostProcessorTestBean bean) {
        System.out.println("FactoryBeanTest有参构造方法");
        this.bean = bean;
    }

    @Override
    public BeanFactoryPostProcessorTestBean getObject() throws Exception {
        System.out.println("FactoryBeanTest > FactoryBean.getObject()方法执行");
        if (bean == null) {
            System.out.println("bean is null, will create a new bean!");
            bean = new BeanFactoryPostProcessorTestBean("bean name", "getObject create a bean with desc");
        }
        return bean;
    }

    @Override
    public Class<?> getObjectType() {
        return BeanFactoryPostProcessorTestBean.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("FactoryBeanTest > InitializingBean.afterPropertiesSet()方法执行");
    }
}
