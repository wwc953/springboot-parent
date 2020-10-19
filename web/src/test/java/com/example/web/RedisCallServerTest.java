package com.example.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/10/19 15:16
 */

@SpringBootTest(classes = FirstApplication.class)
@AutoConfigureMockMvc
public class RedisCallServerTest {

    @Test
    public void test() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Object eval = jedis.eval("return redis.call('info','server')");
        System.out.println(eval.toString());
    }

    @Test
    public void jedisClusterCall() {
        HostAndPort hostAndPort = new HostAndPort("192.168.43.196", 30001);
        JedisCluster jedisCluster = new JedisCluster(hostAndPort);
        Object eval = jedisCluster.eval("return redis.call('info','all')", 0, "");
        System.out.println(eval.toString());
    }

}
