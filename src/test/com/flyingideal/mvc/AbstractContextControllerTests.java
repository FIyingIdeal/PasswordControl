package com.flyingideal.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Administrator on 2017/6/14.
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring/spring-mvc.xml"})
public class AbstractContextControllerTests {

    @Autowired
    protected WebApplicationContext wac;
}
