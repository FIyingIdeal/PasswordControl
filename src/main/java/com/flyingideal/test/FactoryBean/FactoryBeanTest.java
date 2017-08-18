package com.flyingideal.test.FactoryBean;

import com.flyingideal.model.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by Administrator on 2017/6/30.
 */
public class FactoryBeanTest implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        User user = new User("FactoryBeanTest", "FactoryBean");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
