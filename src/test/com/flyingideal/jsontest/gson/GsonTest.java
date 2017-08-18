package com.flyingideal.jsontest.gson;

import com.google.gson.Gson;
import org.junit.Test;

/**
 * Created by Administrator on 2017/3/20.
 */
public class GsonTest {

    @Test
    public void bean2Json() {
        Gson gson = new Gson();
        GsonBean bean = new GsonBean("yanchao", 23, 'F');
        String jsonStr = gson.toJson(bean);
        System.out.println(jsonStr);
        //{"username":"yanchao","age":23,"sex":"F"}
    }

    @Test
    public void json2Bean() {
        Gson gson = new Gson();
        String jsonStr = "{\"name\":\"yanchao\",\"age\":23,\"UserSex\":\"F\"}";
        GsonBean bean = gson.fromJson(jsonStr, GsonBean.class);
        System.out.println(bean);
        //GsonBean{username='yanchao', age=23, sex=F}
    }
}
