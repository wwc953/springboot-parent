package com.example.web.controller;

import com.example.web.bean.User;
import com.example.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    private IHelloService helloService;

    @GetMapping("/hello")
    public String sayHello() {
        return helloService.sayHello();
    }

    @PostMapping("/getUser")
    public User getUser(@RequestBody User user) {
        String address = user.getAddress();
        user.setAddress("欢迎来到" + address);
        return user;
    }

    @PostMapping("/getUser2")
    public User getUser2(@RequestBody User user, @RequestParam(value = "haha", required = false) String haha) {
        String address = user.getAddress();
        user.setAddress("欢迎来到" + address + haha);
        return user;
    }

    @PostMapping("/getMapper")
    public User getMapper(@RequestParam Map<String,Object> map) {
        String name = map.get("name").toString();
        User user = new User();
        user.setName(name);
        return user;
    }

}
