package com.flyingideal.jsontest.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/20.
 */
public class JacksonTest {

    public String bean2Json(Object bean) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        try {
            jsonStr = mapper.writeValueAsString(bean);
            //System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    public <T> T json2Bean(String jsonStr, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        T jacksonBean = null;
        try {
            jacksonBean = mapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jacksonBean;
    }

    @Test
    public void bean2JsonTest() {
        JacksonBean jacksonBean = new JacksonBean("test", 23, 'F');
        System.out.println(bean2Json(jacksonBean));
    }

    @Test
    public void json2BeanTest() {
        String jsonStr = "{\"UserName\":\"yanchao\", \"Age\":23, \"Sex\":\"F\"}";
        JacksonBean bean = json2Bean(jsonStr, JacksonBean.class);
        System.out.println(bean);

        //error，必须与@JsonProperty指定的key值一致
        //String jsonStr1 = "{\"name\":\"yanchao\", \"age\":23, \"sex\":\"F\"}";
        //JacksonBean bean1 = json2Bean(jsonStr1, JacksonBean.class);
        //System.out.println(bean1);
    }

    @Test
    public void map2JsonTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "123211");
        map.put("success", true);
        System.out.println(bean2Json(map));
    }

    @Test
    public void json2MapTest() {
        String jsonStr = "{\"UserName\":\"yanchao\", \"Age\":23, \"Sex\":\"F\"}";
        Map<String, Object> map = json2Bean(jsonStr, Map.class);
        System.out.println(map);

        Map<String, Object> map1 = (Map<String, Object>)json2Bean(jsonStr, Object.class);
        System.out.println(map);
    }

}
