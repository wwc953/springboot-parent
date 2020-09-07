package com.example.web.controller;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO 任务调度线程池--定时任务
 * @author: wangwc
 * @date: 2020/8/22 14:28
 */
//@RestController
//@RequestMapping("/task")
//@Component
public class TaskController {

    public static void main(String[] args) {

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
                new ScheduledThreadPoolExecutor(3);

        //0表示首次执行任务的延迟时间
        //40表示每次执行任务的间隔时间
        //TimeUnit.MILLISECONDS 间隔单位毫秒
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
//                    try {
//                        System.out.println("---");
//                        Thread.sleep(2000L);
                        System.out.println("------");
                        //throw new RuntimeException("0-0-0");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                System.out.println("=======" + Thread.currentThread().getName());
            }
        }, 0, 1, TimeUnit.SECONDS);

        int activeCount = scheduledThreadPoolExecutor.getActiveCount();


    }

}
