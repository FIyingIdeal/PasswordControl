package com.flyingideal.shirotest;

import org.apache.shiro.codec.Hex;
import org.junit.Test;

public class HexTest {

    @Test
    public void hexEncodeToString() {
        String str = "hello";
        String hexStr = Hex.encodeToString(str.getBytes());
        System.out.println(hexStr);
        String decodeStr = new String(Hex.decode(hexStr));
        System.out.println(decodeStr);
    }
}
