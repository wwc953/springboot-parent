package com.example.web.controller;

import com.example.web.thread.MyCallable;
import com.example.web.threadfactory.GroupThreadFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/8/26 11:09
 */
@RestController
public class CachedThreadPoolController {

    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool(new GroupThreadFactory("cache"));

    @GetMapping("/cache/{sum}")
    public String cache(@PathVariable Integer sum) throws ExecutionException, InterruptedException {
        ArrayList<MyCallable> taskList = new ArrayList<MyCallable>();
        for (int i = 0; i < sum; i++) {
            taskList.add(new MyCallable(i));
        }
        long start = System.currentTimeMillis();
        //批量任务 不限时
        List<Future<String>> futures = cachedThreadPool.invokeAll(taskList);
        long end1 = System.currentTimeMillis() - start;

//        for (int i = 0; i < futures.size(); i++) {
//            System.out.println(futures.get(i).get());
//        }

//        futures.stream().forEach(v -> {
//            try {
//                System.out.println(v.get());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });

        long end2 = System.currentTimeMillis() - start;

        System.out.println("任务执行时间：" + end1);
        System.out.println("任务结果展示时间：" + end2);


//        String result = futures.stream().map(v -> {
//            try {
//                return v.get();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return "-9999";
//        }).collect(Collectors.toList()).toString();
//        System.out.println(result);
//
//        futures.stream().map(v -> {
//            try {
//                return v.get();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return "-9999";
//        }).forEach(System.out::println);

        return "success";
    }
}
