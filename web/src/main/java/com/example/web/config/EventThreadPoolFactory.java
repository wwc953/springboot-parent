package com.example.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("classpath:/threadPool.properties")
@EnableAsync
public class EventThreadPoolFactory {
    private static final Logger log = LoggerFactory.getLogger(EventThreadPoolFactory.class);

    @Value("${thread.coreSize}")
    private Integer coreSize;
    @Value("${thread.maxSize}")
    private Integer maxSize;
    @Value("${thread.queueCapacity}")
    private Integer queueCapacity;
    @Value("${thread.keepAliveSeconds}")
    private Integer keepAliveSeconds;


    @Bean
    @ConditionalOnProperty(value = "thread.myTaskAsyncPool", havingValue = "true")
    public Executor myTaskAsyncPool() {
        System.out.println("myTaskAsyncPool init...............");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(coreSize);//配置核心线程数
        executor.setMaxPoolSize(maxSize);//配置核心线程数
        executor.setQueueCapacity(queueCapacity);//配置队列容量
        executor.setKeepAliveSeconds(keepAliveSeconds);//设置线程活跃时间
        executor.setThreadNamePrefix("myTaskAsyn-");//设置线程名
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //设置拒绝策略
        executor.initialize();
        return executor;
    }

    @Bean
    @ConditionalOnProperty(value = "thread.redisTaskPool", havingValue = "true")
    public Executor redisTaskPool() {
        System.out.println("redisTaskPool init...............");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(coreSize);//配置核心线程数
        executor.setMaxPoolSize(maxSize);//配置核心线程数
        executor.setQueueCapacity(queueCapacity);//配置队列容量
        executor.setKeepAliveSeconds(keepAliveSeconds);//设置线程活跃时间
        executor.setThreadNamePrefix("redisTaskAsyn-");//设置线程名
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //设置拒绝策略
        executor.initialize();
        return executor;
    }
}
