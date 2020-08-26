package com.example.web.thread;

import java.util.concurrent.Callable;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/8/22 13:51
 */
public class MyCallable implements Callable<String> {

    private Integer i = 1;

    public MyCallable() {
    }

    public MyCallable(Integer i) {
        this.i = i;
    }

    @Override
    public String call() throws Exception {
        long time = 10L * i;
        Thread.sleep(time);
        System.out.println(Thread.currentThread().getName() + "------------------" + time);
        return String.valueOf(time);
    }
}
