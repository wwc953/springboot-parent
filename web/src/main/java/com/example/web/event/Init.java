package com.example.web.event;

import com.example.web.service.TaskService;
import com.example.web.threadfactory.RedisThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/9/8 14:57
 */
@Component
public class Init implements ApplicationListener<ApplicationReadyEvent> {

    //    @Autowired
//    TaskService taskService;
    private int corePoolsize = 5;
    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
            new ScheduledThreadPoolExecutor(corePoolsize, new RedisThreadFactory("redisRead"));

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
//        taskService.init();
//        System.out.println("init ok");
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            scheduledThreadPoolExecutor.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "------");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, 0, 500, TimeUnit.MILLISECONDS);

        }
    }

}
