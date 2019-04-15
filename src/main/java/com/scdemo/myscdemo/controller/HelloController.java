package com.scdemo.myscdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hupen
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String index() {
        return "Hello World!!!";
    }

    @RequestMapping("/")
    public String index(ModelMap modelMap) {
        // 加入一个属性 用来在模板中读取
        modelMap.addAttribute("host", "http://blog.didispace.com");
        // return 模板文件的名称 对应 src/main/resources/templates/index.html
        return "index";
    }
}
