package com.flyingideal.spring.ioc;

import java.util.Arrays;

/**
 * @author yanchao
 * @date 2018/4/11 16:57
 */
public class IocTest {

    private Class[] classes;

    public Class[] getClasses() {
        return classes;
    }

    public void setClasses(Class[] classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "IOCTest{" +
                "classes=" + Arrays.toString(classes) +
                '}';
    }
}
