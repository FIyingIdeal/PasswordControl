package com.flyingideal.applicationtest.other;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public class JavaGetFile {

    static List<String> fileList = new ArrayList<String>();

    public static void main(String[] args) {
        String path = "F:\\movie";
        System.out.println(getFiles(path));
    }

    public static List getFiles(String filePath) {

        File fileDic = new File(filePath);
        File[] files = fileDic.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getFiles(file.getAbsolutePath());
            }
            fileList.add(file.getAbsolutePath());
        }
        return fileList;
    }
}
