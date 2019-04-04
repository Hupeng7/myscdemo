package com.scdemo.myscdemo;

import com.scdemo.myscdemo.controller.BlogProperties;
import com.scdemo.myscdemo.controller.HelloController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MyscdemoApplicationTests {
    private MockMvc mvc;

    @Autowired
    private BlogProperties blogProperties;

    @Before
    public void setMvc() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")));
    }

    @Test
    public void getBlog() throws Exception {
        Assert.assertEquals(blogProperties.getName(), "Leo");
        Assert.assertEquals(blogProperties.getTitle(), "学好Spring Boot");

    }

    @Test
    public void getBlogValues() {
        System.out.println(blogProperties.getDesc());
        System.out.println(blogProperties.getNumber());
        System.out.println(blogProperties.getBignumber());
        System.out.println(blogProperties.getTest1());
        System.out.println(blogProperties.getTest2());
        System.out.println(blogProperties.getUuid());
    }


    @Test
    public void contextLoads() {
    }

}
