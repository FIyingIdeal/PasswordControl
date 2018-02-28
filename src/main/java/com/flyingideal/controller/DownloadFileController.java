package com.flyingideal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * 文件下载测试
 */
@Controller
@RequestMapping("/download")
public class DownloadFileController {

    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * Excel文件下载功能，直接通过HttpServletResponse将二进制流写出去
     * @param response
     * @throws Exception
     */
    @GetMapping("/excel")
    public void downloadExcel(HttpServletResponse response) throws Exception {
        // 123.xlsx需要在resources目录下，测试的时候如果不存在的话需要重新建立
        String fileName = "123.xlsx";
        Resource resource = resourceLoader.getResource("classpath:123.xlsx");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");//后边的charset必须要添加，否则可能导致下载的Excel无法打开
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        OutputStream os = response.getOutputStream();
        FileCopyUtils.copy(resource.getInputStream(), os);
    }


    /**
     * Excel文件下载功能，通过ResponseEntity返回文件流
     * @return
     */
    @GetMapping("/excel2")
    public ResponseEntity<byte[]> downloadExcel2() {
        String fileName = "123.xlsx";
        Resource resource = resourceLoader.getResource("classpath:123.xlsx");
        HttpHeaders headers = new HttpHeaders();
        // 由于xlsx文件对应的ContentType在MediaType种没有包含，可以自己构造一个MediaType
        headers.setContentType(new MediaType("application", "vnd.ms-excel", Charset.defaultCharset()));
        // 如果MediaType中存在对应的ContentType的话，可直接引用
        // headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", fileName);
        try {
            return new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(resource.getFile()), headers, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
