package com.example.web.controller;

import com.example.web.listener.RedisEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/9/8 16:09
 */
@RestController
public class EventController {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

//    @Autowired
//    ApplicationContext applicationContext;

    @GetMapping(value = "/sendEvent/{message}")
    public String send1(@PathVariable String message) {
        applicationEventPublisher.publishEvent(new RedisEvent(this, message));
//        applicationContext.publishEvent(new RedisEvent(this, message));
        return "send success";
    }

}
