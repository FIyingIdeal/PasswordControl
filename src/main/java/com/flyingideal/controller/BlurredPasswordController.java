package com.flyingideal.controller;

import com.flyingideal.model.BlurredPassword;
import com.flyingideal.service.BlurredPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2016/1/23.
 */
@RestController
@RequestMapping(value = "/blurredpassword")
public class BlurredPasswordController {
    @Autowired
    private BlurredPasswordService blurredPasswordService;

    @RequestMapping(value = "/reletedSubject/{subject}", method = RequestMethod.GET)
    public List<String> getReletedSubjectList(@PathVariable String subject) {
        return blurredPasswordService.getReletedSubject(subject);
    }

    @RequestMapping(value = "/blurredPassword/{subject}", method = RequestMethod.GET)
    public List<BlurredPassword> getBlurredPassword(@PathVariable String subject) {
        return blurredPasswordService.getBlurredPassword(subject);
    }

    @RequestMapping(value = "/exceptionexample", method = RequestMethod.GET)
    public String ExceptionAdviceTest() throws Exception{
        boolean flag = true;
        System.out.println("Inside Exception");
        if (flag) {
            throw new Exception("燕潮");
        }
        return "view";
    }
}