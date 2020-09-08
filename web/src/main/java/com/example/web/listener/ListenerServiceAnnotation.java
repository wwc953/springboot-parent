package com.example.web.listener;

import com.example.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.CompletableFuture;

@Component
public class ListenerServiceAnnotation {

    @Autowired
    IHelloService helloService;

    @Async("redisTaskPool")
    @EventListener(condition = "#redisEvent.message=='xxx'")
    //@EventListener(condition = "#{arg}.{filde}")//添加过滤条件
    public void insertData(RedisEvent redisEvent) {
        String planNo = redisEvent.getMessage();
        System.out.println("Annotation接受到RedisEvent事件,message:" + planNo);
        System.out.println(Thread.currentThread().getName() + " --------------begin---" + planNo);
//        try {
//            String res = helloService.sayHello();
//            System.out.println(res);
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        long begin = System.currentTimeMillis();

        CompletableFuture<String> fu1 = CompletableFuture.supplyAsync(() -> {
            return helloService.sayHello();
        });
        CompletableFuture<String> fu2 = CompletableFuture.supplyAsync(() -> {
            return helloService.insert1();
        });
        CompletableFuture<String> fu3 = CompletableFuture.supplyAsync(() -> {
            return helloService.insert2();
        });
        CompletableFuture<String> fu4 = CompletableFuture.supplyAsync(() -> {
            return helloService.insert3();
        });
        CompletableFuture.allOf(fu1, fu2, fu3, fu4).join();
        System.out.println(Thread.currentThread().getName() + "insertData time:" + (System.currentTimeMillis() - begin));

        System.out.println(Thread.currentThread().getName() + " --------------end---" + planNo);
    }

}
