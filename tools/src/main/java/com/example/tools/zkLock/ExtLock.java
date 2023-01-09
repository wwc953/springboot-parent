package com.example.tools.zkLock;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/7/31 16:37
 */
public interface ExtLock {
    //ExtLock基于zk实现分布式锁
    void  lock();

    //释放锁
    void unLock();
}
