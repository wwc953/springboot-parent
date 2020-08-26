package com.example.web.controller;

import com.example.web.thread.MyCallable;
import com.example.web.threadfactory.GroupThreadFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/8/26 11:09
 */
@RestController
public class CachedThreadPoolController {

    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool(new GroupThreadFactory("cache"));

    @GetMapping("/cache/{sum}")
    public void cache(@PathVariable Integer sum) {
        ArrayList<MyCallable> taskList = new ArrayList<MyCallable>();
        for (int i = 0; i < sum; i++) {
            taskList.add(new MyCallable(i));
        }
        try {
            long start = System.currentTimeMillis();
            List<Future<String>> futures = cachedThreadPool.invokeAll(taskList);
            long end1 = start - System.currentTimeMillis();

            for (int i = 0; i < futures.size(); i++) {
                System.out.println(futures.get(i).get());
            }
            long end2 = start - System.currentTimeMillis();
            System.out.println("任务执行时间：" + end1);
            System.out.println("任务结果展示时间：" + end2);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
