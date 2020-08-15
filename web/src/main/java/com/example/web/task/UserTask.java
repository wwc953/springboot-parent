package com.example.web.task;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/8/15 14:08
 */
public class UserTask implements Runnable {

    private CountDownLatch countDownLatch;

    public UserTask() {

    }

    public UserTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "任务执行结束==========");
        countDownLatch.countDown();
    }
}
