package com.example.web;

import com.example.web.redisSeq.RedisSeq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/9/24 11:36
 */
@SpringBootTest(classes = FirstApplication.class)
@AutoConfigureMockMvc
public class RedisId {

    @Autowired
    RedisSeq redisSeq;

    @Test
    public void contextLoads(){
        String key = "hahah";
        Long id = redisSeq.getId(key);
        System.out.println("id ====> "+id);
    }

    @Test
    public void batchGetId(){
        String key = "hahah";
        List<Long> getIds = redisSeq.getId_batch(key, 100);
        System.out.println("ids ====> "+getIds);
    }

}
