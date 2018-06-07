package com.flyingideal.model;

/**
 * @author yanchao
 * @date 2018/4/11 15:06
 */
public class BeanFactoryPostProcessorTestBean {

    private String name;
    private String desc;

    public BeanFactoryPostProcessorTestBean() {
    }

    public BeanFactoryPostProcessorTestBean(String name, String desc) {
        System.out.println("BeanFactoryPostProcessorTestBean构造方法执行");
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BeanFactoryPostProcessorTestBean{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
