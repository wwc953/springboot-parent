package com.example.web.listener;

import com.example.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ListenerServiceAnnotation {

    @Autowired
    IHelloService helloService;

    @Async("redisTaskPool")
    @EventListener
    public void insertData(RedisEvent redisEvent) {
        String planNo = redisEvent.getMessage();
        System.out.println("Annotation接受到RedisEvent事件,message:" + planNo);
        System.out.println(Thread.currentThread().getName() + " --------------begin---" + planNo);
        try {
            String res = helloService.sayHello();
            System.out.println(res);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " --------------end---" + planNo);
    }

}
