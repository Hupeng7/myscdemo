package com.scdemo.myscdemo.controller;

import com.scdemo.myscdemo.entity.UserXml;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName UseXmlController
 * @Description TODO
 * @Author hupen
 * @Date 2019/4/16 14:22
 * @Version 1.0
 */
@Controller
public class UseXmlController {
    @PostMapping(value = "/userXml",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public UserXml create(@RequestBody UserXml userXml) {
        userXml.setName("hi: " + userXml.getName());
        userXml.setAge(userXml.getAge() + 100);
        return userXml;
    }


}
