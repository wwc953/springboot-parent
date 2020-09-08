package com.example.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/9/8 16:56
 */
@Configuration
@EnableAsync
public class EventThreadPoolFactory {
    private static final Logger log = LoggerFactory.getLogger(EventThreadPoolFactory.class);

    @Bean
    public Executor myTaskAsyncPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);//配置核心线程数
        executor.setMaxPoolSize(8);//配置核心线程数
        executor.setQueueCapacity(1000);//配置队列容量
        executor.setKeepAliveSeconds(60);//设置线程活跃时间
        executor.setThreadNamePrefix("myTaskAsyn-");//设置线程名
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //设置拒绝策略
        executor.initialize();
        return executor;
    }

    @Bean
    public Executor redisTaskPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);//配置核心线程数
        executor.setMaxPoolSize(8);//配置核心线程数
        executor.setQueueCapacity(1000);//配置队列容量
        executor.setKeepAliveSeconds(60);//设置线程活跃时间
        executor.setThreadNamePrefix("redisTaskAsyn-");//设置线程名
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //设置拒绝策略
        executor.initialize();
        return executor;
    }
}
