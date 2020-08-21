package com.example.web.controller;

import com.example.web.threadfactory.GroupThreadFactory;
import com.example.web.thread.UserTask;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/8/15 13:59
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * cpu个数
     */
    private int corePoolSize = Runtime.getRuntime().availableProcessors();

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, corePoolSize << 1,
            300L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(16),
            new GroupThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @GetMapping("/more/{count}")
    public String more(@PathVariable Integer count) throws InterruptedException {
        System.out.println("========== corePoolSize：" + corePoolSize);
        if (count == null || count <= 0) {
            count = 2;
        }
        CountDownLatch countDownLatch = new CountDownLatch(count);
        long start = System.currentTimeMillis();
        System.out.println("-----开始" + start);
        for (int i = 0; i < count; i++) {
            threadPoolExecutor.execute(new UserTask(countDownLatch));
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("-----结束" + time);
        return "success: " + time;
    }

}
