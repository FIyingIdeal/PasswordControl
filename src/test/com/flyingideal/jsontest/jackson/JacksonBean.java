package com.flyingideal.jsontest.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrator on 2017/3/20.
 */
public class JacksonBean {
    @JsonProperty("UserName")
    private String name;
    @JsonProperty("Age")
    private int age;
    @JsonProperty("Sex")
    private char sex;

    public JacksonBean() {}

    public JacksonBean(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "JavaBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
