package com.flyingideal.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/23.
 * 利用Jackson实现JavaBean与Json的转化
 */
public class BeanJsonConverter {

    /**
     * 将JavaBean转换为Json字符串
     * @param bean
     * @return
     * @throws JsonProcessingException
     */
    public static String bean2Json(Object bean) throws JsonProcessingException {
        if (bean == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(bean);
    }

    /**
     * 将Json转为JavaBean，返回指定类型T的实例
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T json2Bean(String jsonStr, Class<T> clazz) throws IOException {
        if (jsonStr == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonStr, clazz);
    }

    /**
     * 将Json转换为JavaBean，返回Object实例
     * @param jsonStr
     * @return
     * @throws IOException
     */
    public static Object json2Bean(String jsonStr) throws IOException {
        if (jsonStr == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonStr, Object.class);
    }

}
