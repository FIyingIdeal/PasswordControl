package com.flyingideal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/redirect")
public class RedirectController {

    @GetMapping("/302")
    public Object firstRequest(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("in first request");
        /*try {
            WebUtils.redirectToSavedRequest(request, response, "https://www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        response.setStatus(302);
        response.setHeader("Location", "http://localhost:8080/redirect/target");
        return "firect request";
    }

    @GetMapping("/middle")
    public void secondRequest(HttpServletResponse response) {
        System.out.println("in second request");
        response.setStatus(302);
        response.setHeader("Location", "http://localhost:8080/redirect/target");
    }

    @GetMapping("/target")
    @ResponseBody
    public String target() {
        return "in target method";
    }
}
