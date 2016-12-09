package com.flyingideal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/9.
 */
@RestController
public class UploadFileController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Object uploadFile(@RequestParam(value = "file") MultipartFile[] files, HttpServletRequest request) {
        //如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
        //如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
        //并且上传多个文件时，前台表单中的所有<input type="file"/>的name都应该是file，否则参数里的file无法获取到所有上传的文件
        String fileDicPath = request.getSession().getServletContext().getRealPath("/upload");
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    file.transferTo(new File(fileDicPath + File.separator + file.getOriginalFilename()));
                } catch(IOException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            } else {
                //Todo:空文件提示
                continue;
            }
        }
        //Todo:返回值封装类（暂时未定义）处理
        return "文件上传成功";
    }
}
