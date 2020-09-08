package com.example.service.impl;

import com.example.service.IHelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello() {
        return "hello hhhh";
    }

    boolean flag = true;

    @Override
    public String insert1() {
        System.out.println(Thread.currentThread().getName() + "dao --> insert1 -- beg");
        if(flag) sleep(5000);
        System.out.println(Thread.currentThread().getName() + "dao --> insert1 -- end");
        return "1000";
    }

    @Override
    public String insert2() {
        System.out.println(Thread.currentThread().getName() + "dao --> insert2 --beg");
        if(flag) sleep(2000);
        System.out.println(Thread.currentThread().getName() + "dao --> insert2 --end");
        return "2000";
    }

    @Override
    public String insert3() {
        System.out.println(Thread.currentThread().getName() + "dao --> insert3 -- beg");
        if(flag) sleep(3000);
        System.out.println(Thread.currentThread().getName() + "dao --> insert3 -- end");
        return "3000";
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
