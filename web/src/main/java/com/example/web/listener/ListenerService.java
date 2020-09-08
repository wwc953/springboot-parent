package com.example.web.listener;

import com.example.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

//@Component
public class ListenerService implements ApplicationListener<RedisEvent> {

    @Autowired
    IHelloService helloService;

    public void insertData(String planNo) {
        System.out.println(Thread.currentThread().getName()+" --------------begin---"+planNo);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" --------------end---"+planNo);
    }

//    @Async
    @Override
    public void onApplicationEvent(RedisEvent event) {
        String message = event.getMessage();
        System.out.println("接受到RedisEvent事件,message:" + message);
        if (!StringUtils.isEmpty(message)) {
            insertData(message);
        }

    }
}
