package com.scdemo.myscdemo.controller;

import com.scdemo.myscdemo.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hupen
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/index1")
    public String index1(ModelMap modelMap) {
        // 加入一个属性 用来在模板中读取
        modelMap.addAttribute("host", "http://blog.didispace.com");
        // return 模板文件的名称 对应 src/main/resources/templates/index.html
        return "index";
    }

    @RequestMapping("/helloError")
    public String helloError() throws Exception {
        throw new Exception("发生错误...");
    }

    @RequestMapping("/helloError2")
    public String helloError2() throws MyException {
        throw new MyException("发生错误2");
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
