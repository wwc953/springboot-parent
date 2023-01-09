package com.example.web.controller;

import com.example.service.IHelloService;
import com.example.tools.zkLock.ExtLock;
import com.example.web.bean.User;
import com.example.web.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ZKController {

    @Autowired
    ExtLock extLock;

    @GetMapping(value = "/zk/lock/{message}")
    public String send1(@PathVariable String message) {
        System.out.println("进入");
        extLock.lock();
        return "send1 success";
    }


}
