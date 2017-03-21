package com.flyingideal.jsontest.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/3/20.
 */
public class GsonBean {

    @SerializedName(value = "name", alternate = {"username", "userName", "UserName"})
    private String username;
    @SerializedName(value="age", alternate = {"Age", "userAge", "UserAge"})
    private int age;
    @SerializedName(value="sex", alternate = {"Sex", "userSex", "UserSex"})
    private char sex;

    public GsonBean() {}

    public GsonBean(String username, int age, char sex) {
        this.username = username;
        this.age = age;
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return "GsonBean{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
