package com.example.web.redisSeq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/9/24 9:22
 */
@Component
//implements InitializingBean
public class RedisSeq {

//    @Autowired
//    private JedisConnectionFactory jedisConnectionFactory;
//    private JedisPool jedisPool;
//
//
//    public Jedis getJedis() {// 记的关闭jedis
//        if (jedisPool != null) {
//            return jedisPool.getResource();
//        } else {
//            try {
//                afterPropertiesSet();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return jedisPool.getResource();
//        }
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        Field poolField = ReflectionUtils.findField(JedisConnectionFactory.class, "pool");
//        ReflectionUtils.makeAccessible(poolField);
//        jedisPool = (JedisPool) ReflectionUtils.getField(poolField, jedisConnectionFactory);
//    }

    @Autowired
    RedisTemplate redisTemplate;

    public Long getAtomicId(String tableName) {
        Assert.notNull(tableName, "TableName is null");
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(tableName, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        return increment;
    }

    public Long getId(String key, String hashKey) {
        Assert.notNull(key, "key is null");
        Assert.notNull(key, "hashKey is null");
        Long increment = redisTemplate.opsForHash().increment(key, hashKey, 1L);
        return increment;
    }

    public Long getId(String key) {
        Assert.notNull(key, "key is null");
        Long increment = redisTemplate.opsForValue().increment(key);

        return increment;
    }

    public List<Long> getId_batch(String key,Integer count) {
        Assert.notNull(key, "key is null");

        List<Long> list = redisTemplate.executePipelined(new RedisCallback<Long>() {

            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                for (int i = 0; i < count; i++) {
                    try {
                        connection.incr(key.getBytes( "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });

        return list;
    }

    public Long getId2(String key) {
        Assert.notNull(key, "key is null");
        Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        Long incr = jedis.incr(key);
        jedis.close();
        return incr;
    }

    public List<Long> batchGetId(String key, Integer count) {
        Assert.notNull(key, "key is null");
        Assert.notNull(count, "count is null");
        Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        Pipeline pip = jedis.pipelined();
        for (int i = 0; i < count; i++) {
            pip.incr(key);
        }
        List<Object> objects = pip.syncAndReturnAll();
        List<Long> longList = objects.stream().map(id -> Long.parseLong(String.valueOf(id))).collect(Collectors.toList());
        jedis.close();
        return longList;
    }


}
