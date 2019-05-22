package com.scdemo.myscdemo.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "aop demo", notes = "aop例子")
    @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "path", dataType = "String")
    @RequestMapping(value = "/helloAop", method = RequestMethod.GET)
    @ResponseBody
    public String helloAop(@RequestParam String name) {
        return "hello aop " + name;
    }


}
