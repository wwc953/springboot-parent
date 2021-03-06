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
//        String ip = "192.168.43.196";
        String ip = "192.168.43.240";
        int port = 30001;
//        int port = 8001;
        HostAndPort hostAndPort = new HostAndPort(ip, port);
        JedisCluster jedisCluster = new JedisCluster(hostAndPort);
        Object eval = jedisCluster.eval("return redis.call('info','all')", 0, "");
        System.out.println(eval.toString());
//        jedisCluster.set("aa", "aa");
//        jedisCluster.get("aa");
    }

    @Test
    public void jedisClusterNodesCall() {
        String ip = "192.168.43.240";
        int port = 30001;
        HostAndPort hostAndPort = new HostAndPort(ip, port);
        JedisCluster jedisCluster = new JedisCluster(hostAndPort);
        Object eval = jedisCluster.eval("return redis.call('cluster','nodes')", 0, "");
        System.out.println(eval.toString());
    }

}
