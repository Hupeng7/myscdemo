package com.scdemo.myscdemo;

import com.scdemo.myscdemo.controller.BlogProperties;
import com.scdemo.myscdemo.controller.UserController;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MyscdemoApplicationTests {
    private MockMvc mvc;

    @Autowired
    private BlogProperties blogProperties;

//    @Before
//    public void setMvc() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
//    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON))
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

    /**
     * 对不同的controller类方法测试之前 需要初始化对应的controller类
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void testUserController() throws Exception {
        RequestBuilder request = null;

        // 1 get查一下user列表，应该为空
        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        //2. post提交有个user
        request = post("/users/")
                .param("id", "1")
                .param("name", "单元测试大师")
                .param("age", "20");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        //3. get获取user列表，应该有刚才插入的数据
        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"单元测试大师\",\"age\":20}]")));

        //4. put修改id为1的user
        request = put("/users/1")
                .param("name", "单元测试终极大师")
                .param("age", "30");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        //5. get一个id为1的user
        request = get("/users/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"单元测试终极大师\",\"age\":30}")));

        //6. del删除id为1的user
        request = delete("/users/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        //7. get查一下user列表，应该为空
        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

    }


}
