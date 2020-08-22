package com.example.web.controller;

import com.example.web.thread.MyCallable;
import com.example.web.threadfactory.GroupThreadFactory;
import com.example.web.thread.UserTask;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
        System.out.println("-----countDownLatch开始" + start);
        for (int i = 0; i < count; i++) {
            threadPoolExecutor.execute(new UserTask(countDownLatch, i));
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("-----countDownLatch结束" + end);
        long time = end - start;
        System.out.println("-----countDownLatch 耗时：" + time);
        return "success: " + time;
    }

    @GetMapping("/more2/{count}")
    public String callable(@PathVariable Integer count) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        System.out.println("-----callable 开始" + start);
        List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Future<String> future = threadPoolExecutor.submit(new MyCallable(i));
            arrayList.add(future.get());
        }
        long end = System.currentTimeMillis();
        System.out.println("-----callable结束" + end);
        long time = end - start;
        System.out.println("-----callable耗时：" + time);
        return "success: " + arrayList.toString();
    }

}
