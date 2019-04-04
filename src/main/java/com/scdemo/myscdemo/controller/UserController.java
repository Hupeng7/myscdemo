package com.scdemo.myscdemo.controller;

import com.scdemo.myscdemo.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author hupen
 * @Date 2019/4/4 17:32
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    /**
     * 创建线程安全的Map
     */
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUserList() {
        List<User> userList = new ArrayList<User>(users.values());
        return userList;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }


}
