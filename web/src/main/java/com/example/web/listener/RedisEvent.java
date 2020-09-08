package com.example.web.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @Description: TODO 自定义事件
 * @author: wangwc
 * @date: 2020/9/8 16:00
 */
public class RedisEvent extends ApplicationEvent {
    private String message;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public RedisEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
