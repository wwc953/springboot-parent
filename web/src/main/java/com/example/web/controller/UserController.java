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

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
            300L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10),
            new GroupThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @GetMapping("/more/{count}")
    public String more(@PathVariable Integer count) throws InterruptedException {
        if (count == null || count <= 0) {
            count = 2;
        }
        CountDownLatch countDownLatch = new CountDownLatch(count);
        System.out.println("-----开始" + count);
        for (int i = 0; i < count; i++) {
            threadPoolExecutor.execute(new UserTask(countDownLatch));
        }
        countDownLatch.await();
        System.out.println("-----结束");
        return "success: " + count;
    }

}
