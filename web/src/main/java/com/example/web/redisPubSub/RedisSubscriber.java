package com.example.web.redisPubSub;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * Redis消息监听 (可用于刷新本地缓)
 */
@Component
public class RedisSubscriber extends MessageListenerAdapter {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("刷新本地缓存开始...");
        System.out.println("msg:" + message + "，pattern：" + new String(pattern));
        System.out.println("刷新本地缓存结束...");
    }
}
