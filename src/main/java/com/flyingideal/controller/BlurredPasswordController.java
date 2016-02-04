package com.flyingideal.controller;

import com.flyingideal.model.BlurredPassword;
import com.flyingideal.service.BlurredPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/1/23.
 */
@Controller
@RequestMapping(value = "/blurredpassword")
public class BlurredPasswordController {
    @Autowired
    private BlurredPasswordService blurredPasswordService;

    @RequestMapping(value = "/reletedSubject/{subject}", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getReletedSubjectList(@PathVariable String subject) {
        return blurredPasswordService.getReletedSubject(subject);
    }

    @RequestMapping(value = "/blurredPassword/{subject}", method = RequestMethod.GET)
    @ResponseBody
    public List<BlurredPassword> getBlurredPassword(@PathVariable String subject) {
        return blurredPasswordService.getBlurredPassword(subject);
    }

}

