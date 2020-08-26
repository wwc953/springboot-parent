package com.example.web.lamda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/8/26 16:10
 */
public class LamdaTest {
    public static void main(String[] args) {
//        Map<String, String> hashMap = new ConcurrentHashMap<>();
        Map<String, String> hashMap = new HashMap<>();
        Object obj = new Object();
        Lock lock = new ReentrantLock();
        ArrayList<Integer> list = new ArrayList<Integer>() {{
            add(1000);
            add(12000);
            add(2000);
            add(1300);
            add(400);
            add(7020);
        }};

        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    synchronized (obj) {

                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                        hashMap.clear();
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                        list.stream().forEach(v -> hashMap.put(String.valueOf(v), String.valueOf(v)));
//                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
//                    try {
//                        Thread.sleep(10L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    System.out.println(Thread.currentThread().getName() + "," + hashMap.get("1300"));
                }
            }).start();
        }

        System.out.println(Thread.currentThread().getName() + "=====" + hashMap.toString());

    }

}
