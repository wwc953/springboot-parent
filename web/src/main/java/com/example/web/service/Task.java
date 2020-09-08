package com.example.web.service;

import com.example.web.utils.SpringUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Runnable {
    @Override
    public void run() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssSSS");
//        System.out.println("------------"+simpleDateFormat.format(new Date()));
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        if (TaskService.scheduledThreadPoolExecutor.getActiveCount() % 2 == 1) {
//            throw new RuntimeException("=-=-=");
//        }
        try {
//            int max = 100;
//            int min = 1;
//            int ran2 = (int) (Math.random() * (max - min) + min);
//            if (ran2 % 2 == 0) {
//                int iii = 1 / 0;
//            }

            StringRedisTemplate redisTemplate = (StringRedisTemplate) SpringUtil.getBean("stringRedisTemplate");
            String msg = redisTemplate.opsForList().rightPop("msg");
            if (!StringUtils.isEmpty(msg)) {
                System.out.println("msg:::" + msg);

                if ("1".equals(msg)) {
                    System.out.println(Thread.currentThread().getName() + "error");
                    throw new RuntimeException("xxxxx");
                }

                TaskService taskService = (TaskService) SpringUtil.getBean("taskService");
//                taskService.insertData();
            } else {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "msg is null,睡眠一秒");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


//        System.out.println(Thread.currentThread().getName() + "子线程");
//        TaskService.isrunning();
        System.out.println("------------" + simpleDateFormat.format(new Date()));
    }

//    public static class ChildThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
//        public void uncaughtException(Thread t, Throwable e) {
//            System.out.println(String.format("handle exception in child thread. %s", e));
//        }
//    }
}
