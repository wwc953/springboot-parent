package com.example.web.thread;

import com.example.web.semaphore.SemaphoreService;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/8/15 15:45
 */
public class MyThread extends Thread {

    private SemaphoreService service;

    public MyThread(String name, SemaphoreService service) {
        super();
        this.setName(name);
        this.service = service;
    }

    @Override
    public void run() {
        this.service.doSomething();
    }

}
