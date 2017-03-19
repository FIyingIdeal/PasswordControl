package com.flyingideal.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2017/3/18.
 */
@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String name) {
        return "Hi, " + name + "!";
    }
}
