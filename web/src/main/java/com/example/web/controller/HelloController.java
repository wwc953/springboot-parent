package com.example.web.controller;

import com.example.web.bean.User;
import com.example.service.IHelloService;
import com.example.web.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    private IHelloService helloService;

    @Autowired
    StringRedisTemplate stringredisTemplate;

    @GetMapping(value = "/send1/{message}")
    public String send1(@PathVariable String message) {
        stringredisTemplate.convertAndSend("TextChannel", message);
        return "send1 success";
    }

    @GetMapping("/task/{msg}")
    public String task(@PathVariable String msg) {
        if ("start".equals(msg))
            TaskService.startTask();
        else
            TaskService.shutdown();
        return msg;
    }

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
    public User getMapper(@RequestParam Map<String, Object> map) {
        String name = map.get("name").toString();
        User user = new User();
        user.setName(name);
        return user;
    }

}
