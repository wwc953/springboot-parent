package com.example.web.threadfactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 自定义线程池(自定义名称)
 * @author: wangwc
 * @date: 2020/8/15 14:19
 */
public class GroupThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private static final AtomicInteger threadNumber = new AtomicInteger(1);
    /**
     * 前缀：自定义名称
     */
    private String mPrefix;

    private boolean mDaemo;

    private ThreadGroup mGroup;

    public GroupThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        mGroup = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        mPrefix = "groupPool-" +
                poolNumber.getAndIncrement() +
                "-thread-";

    }

    public GroupThreadFactory(String mPrefix) {
        this.mPrefix = mPrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, this.mPrefix+ threadNumber.getAndIncrement());
    }
}
