package com.flyingideal;

import org.junit.Test;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by Administrator on 2017/7/13.
 */
public class MyContextClassLoader {

    @Test
    public void test() {
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("Driver : " + driver.getClass() + ", " +
                    "ClassLoader : " + driver.getClass().getClassLoader());
        }

        System.out.println("current thread contextloader:"+Thread.currentThread().getContextClassLoader());
        System.out.println("current loader:" + MyContextClassLoader.class.getClassLoader());
        System.out.println("ServiceLoader loader:" + ServiceLoader.class.getClassLoader());
    }

    @Test
    public void AppClassLoaderTest() {
        System.out.println(System.getProperty("java.class.path"));
    }
}
