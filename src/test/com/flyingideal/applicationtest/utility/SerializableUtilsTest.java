package com.flyingideal.applicationtest.utility;

import com.flyingideal.utility.SerializableUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author yanchao
 * @date 2018/6/7 18:34
 */
public class SerializableUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(SerializableUtilsTest.class);

    /**
     * 如果是内部类实现了Serializable的话，需要将其声明为static，否则报异常java.io.NotSerializableException
     */
    static class Person implements Serializable {

        private String name;
        private int age;

        public Person() {}

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
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

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    @Test
    public void serializeTest() {
        Person person = new Person("test", 18);
        String personSerializedString = SerializableUtils.serialize(person);
        logger.info("Person 序列化结果：{}", personSerializedString);
    }

    @Test
    public void deserializeTest() {
        String personDeserializeString = "rO0ABXNyAERjb20uZmx5aW5naWRlYWwuYXBwbGljYXRpb250ZXN0LnV0aWxpdHkuU2VyaWFsaXphYmxlVXRpbHNUZXN0JFBlcnNvbs5k3YDzZ98uAgACSQADYWdlTAAEbmFtZXQAEkxqYXZhL2xhbmcvU3RyaW5nO3hwAAAAEnQABHRlc3Q=";
        Person person = SerializableUtils.deserialize(personDeserializeString);
        logger.info("Person 反序列化结果：{}", person);
    }
}
