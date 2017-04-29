package com.flyingideal.usertest.other;

/**
 * Created by Administrator on 2017/3/16.
 */
public class StaticClass {
    private String name;

    public StaticClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void test(StaticClass staticClass) {
        System.out.println(staticClass.getName());
    }

    public static void main(String[] args) {
        StaticClass staticClass = new StaticClass("yanchao");
        test(staticClass);
    }
}
