package com.flyingideal.mvc.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.flyingideal.mvc.AbstractContextControllerTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by Administrator on 2017/6/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest extends AbstractContextControllerTests {

    private MockMvc mockMvc;

    private static String URI = "/user/{action}";

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
    }

    @Test
    public void loginTest() throws Exception {
        this.mockMvc.perform(
                post(URI, "login")
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"admin\", \"password\":\"123456\", \"birthday\":\"2017-06-14\"}")
        ).toString();
    }

    @Test
    public void pathVariableDateTest() throws Exception {
        this.mockMvc.perform(
                get(URI, "2017-06-13")
        ).andExpect(content().string("1"));
    }
}
