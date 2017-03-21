package com.flyingideal.jsontest.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/20.
 */
public class JacksonTest {

    @Test
    public void bean2Json() {
        JacksonBean jacksonBean = new JacksonBean("test", 23, 'F');
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        try {
            jsonStr = mapper.writeValueAsString(jacksonBean);
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void json2Bean() {
        String jsonStr = "{\"UserName\":\"yanchao\", \"Age\":23, \"Sex\":\"F\"}";
        String jsonStr1 = "{\"name\":\"yanchao\", \"age\":23, \"sex\":\"F\"}";
        ObjectMapper mapper = new ObjectMapper();
        try {
            JacksonBean jacksonBean = mapper.readValue(jsonStr, JacksonBean.class);
            System.out.println(jacksonBean);
            JacksonBean jacksonBean1 = mapper.readValue(jsonStr1, JacksonBean.class);
            System.out.println(jacksonBean1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
