package com.example.web.event;

import com.example.web.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/9/8 14:57
 */
@Component
public class Init implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    TaskService taskService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
//        taskService.init();
        System.out.println("init ok");

    }

}
