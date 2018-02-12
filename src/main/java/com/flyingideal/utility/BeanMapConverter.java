package com.flyingideal.utility;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2017/6/15.
 */
public class BeanMapConverter {

    /**
     * 将Java Bean转化为{@link Map<String, Object>}
     * @param bean  要被转为Map的Bean
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Map<String, Object> bean2Map(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        if (bean == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String key = propertyDescriptor.getName();
            if (Objects.equals(key, "class")) {
                continue;
            }
            Object value = propertyDescriptor.getReadMethod().invoke(bean);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 将{@link Map<String, Object>}转为Java Bean
     * 但如果被转换的Bean没有无参构造方法，则会抛出异常
     * @param map
     * @param clazz
     * @param <T>
     * @return
     * @throws IntrospectionException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static <T> T map2Bean(Map<String, Object> map, Class<T> clazz)
            throws IntrospectionException, InstantiationException,
                    IllegalAccessException, InvocationTargetException {
        if (map == null || map.size() == 0) {
            return null;
        }
        //需要无参构造方法
        T bean = clazz.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String key = propertyDescriptor.getName();
            if (!map.containsKey(key)) {
                continue;
            }
            propertyDescriptor.getWriteMethod().invoke(bean, map.get(key));
        }
        return bean;
    }
}
