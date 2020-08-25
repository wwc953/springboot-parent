package com.example.web.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/8/15 14:08
 */
public class UserTask implements Runnable {

    private CountDownLatch countDownLatch;

    private Integer i;

    public UserTask() {

    }

    public UserTask(CountDownLatch countDownLatch, Integer i) {
        this.countDownLatch = countDownLatch;
        this.i = i;
    }

    @Override
    public void run() {
//        long time = 1000L * i;
        long time = 1000L;

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "任务执行结束==========" + time);
        countDownLatch.countDown();
    }
}
