package com.flyingideal.applicationtest.other;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Administrator on 2016/8/31.
 */
public class JavaReadUTF8WithBOOM {

    @Test
    public void readUTF8WithBOOM() throws Exception{
        File file = new File("F:/123.txt");
//        UnicodeReader unicodeReader = new UnicodeReader(new FileInputStream(file));
//        BufferedReader bufferedReader = new BufferedReader(unicodeReader);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }

    @Test
    public void StringTest() {
        String s = "hello";
        String s1 = "hello";
        String s2 = new String("hello");
        System.out.println(s == s1);
        System.out.println(s == s2);
    }
}
