package com.example.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Runnable {
    @Override
    public void run() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("------------"+simpleDateFormat.format(new Date()));
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        if ((System.currentTimeMillis() % 2) == 0L) {
//            throw new RuntimeException("=-=-=");
//        }

        System.out.println(Thread.currentThread().getName() + "子线程");
        TaskService.isrunning();
        System.out.println("------------" + simpleDateFormat.format(new Date()));
    }
}
