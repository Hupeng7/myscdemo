package com.scdemo.myscdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloRestController
 * @Description // @RestController = @Controller + @ResponseBody
 * @Author hupen
 * @Date 2019/4/15 17:35
 * @Version 1.0
 */
@RestController
public class HelloRestController {

    @RequestMapping(value = "hello1", method = RequestMethod.GET)
    public String hello() {
        return "Hello World,RestController = @Controller + @ResponseBody";
    }

}
