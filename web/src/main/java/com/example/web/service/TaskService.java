package com.example.web.service;

import com.example.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class TaskService {
    static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
            new ScheduledThreadPoolExecutor(1);

    @Autowired
    IHelloService helloService;

    public void insertData() {
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
    }

//    @PostConstruct
    public void init() {
        System.out.println("----init");
//        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Task(), 1, 2, TimeUnit.SECONDS);

        //scheduleWithFixedDelay 比如当前一个任务结束的时刻，开始结算间隔时间，如0秒开始执行第一次任务，任务耗时5秒，任务间隔时间3秒，那么第二次任务执行的时间是在第8秒开始。
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(new Task(), 0, 500, TimeUnit.MILLISECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(new Task(), 1, 2, TimeUnit.SECONDS);

    }


    @PreDestroy
    public void destroy() {
        System.out.println("destory------------");
//        scheduledThreadPoolExecutor.shutdown();
        scheduledThreadPoolExecutor.shutdownNow();
    }

    public static void startTask() {
        if (!isrunning()) {
            scheduledThreadPoolExecutor.scheduleAtFixedRate(new Task(), 0, 1, TimeUnit.SECONDS);
        }
    }

    public static void shutdown() {
        System.out.println("down");
        scheduledThreadPoolExecutor.shutdown();
    }

    public static boolean isrunning() {
        boolean shutdown = scheduledThreadPoolExecutor.isShutdown();
        System.out.println("shutdown:" + shutdown);

        int activeCount = scheduledThreadPoolExecutor.getActiveCount();
        long taskCount = scheduledThreadPoolExecutor.getTaskCount();
        System.out.println("activeCount:" + activeCount + ",,,taskCount:" + taskCount);
        return activeCount > 0 ? true : false;
    }


}
