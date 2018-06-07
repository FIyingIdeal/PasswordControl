package com.flyingideal.utility;

import org.apache.shiro.io.SerializationException;

import java.io.*;
import java.util.Base64;

/**
 * @author yanchao
 * @date 2018/6/7 17:42
 * 序列化工具类
 */
public class SerializableUtils {

    /**
     * 将一个{@link Serializable}实现类对象序列化为String，但序列化为String的时候会有一些问题，具体参考下边链接：
     * @see <a href="https://bugs.java.com/bugdatabase/view_bug.do?bug_id=4968673">Java BUG</a>
     * @see <a href="https://blog.csdn.net/woshisangsang/article/details/78470416"/>  这篇文章指出，如果使用ByteArrayOutputStream#toString("ISO-8859-1")及String#getBytes("ISO-8859-1")时可以序列化和反序列化成功
     * @see <a href="https://blog.csdn.net/cubesky/article/details/38753861"/>  这篇文章最后指出，如果使用Base64编解码也是可以成功的
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String serialize(T object) {
        try ( ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
              ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream) ) {
            objectOutputStream.writeObject(object);
            // JDK1.8提供了Base64的一个实现，也可以使用Shiro或其他库的Base64实现
            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new SerializationException("对象序列化为String异常", e);
        }
    }

    /**
     * 将序列化后的字符串反序列化为对象
     * @param deserializeString
     * @param <T>
     * @return
     */
    public static <T> T deserialize(String deserializeString) {
        byte[] deserializeBytes = Base64.getDecoder().decode(deserializeString);
        try ( ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(deserializeBytes);
              ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream) ) {
            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new SerializationException("反序列化String到对象时异常", e);
        }
    }
}
