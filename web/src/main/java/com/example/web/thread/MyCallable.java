package com.example.web.thread;

import com.example.web.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/8/22 13:51
 */
public class MyCallable implements Callable<String> {

    private static final Logger log = LoggerFactory.getLogger(MyCallable.class);

    private Integer i = 1;

    public MyCallable() {
    }

    public MyCallable(Integer i) {
        this.i = i;
    }

    @Override
    public String call() {
        log.info("begin......" + Thread.currentThread().getName());
        long time = 10L * i;
        try {
            Thread.sleep(time);
            if (i % 2 == 0) {
                System.out.println("i=" + i + ",,,," + Thread.currentThread().getName());
                int cc = i / 0;
            }
        } catch (Exception e) {
//            log.error("task报错", e);
            e.printStackTrace();
        }

//        System.out.println(Thread.currentThread().getName() + "----------" + time);
        log.info("success......" + Thread.currentThread().getName());
        return String.valueOf(time);
    }
}
