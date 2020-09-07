package com.example.web.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class TaskService {
    static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
            new ScheduledThreadPoolExecutor(1);

    @PostConstruct
    public void init() {
        System.out.println("----init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destory------------");
        scheduledThreadPoolExecutor.shutdown();
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
        System.out.println("shutdown:"+shutdown);

        int activeCount = scheduledThreadPoolExecutor.getActiveCount();
        long taskCount = scheduledThreadPoolExecutor.getTaskCount();
        System.out.println("activeCount:" + activeCount + ",,,taskCount:" + taskCount);
        return activeCount > 0 ? true : false;
    }
}
