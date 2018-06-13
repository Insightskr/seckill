package com.ins.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring/spring-redis.xml"})
public class JedisTest {
    @Autowired
    private JedisPool jedisPool;
    @Test
    public void testJedis(){
//        Jedis jedis = new Jedis("192.168.220.133",6379);
        Jedis jedis = jedisPool.getResource();
        jedis.set("k2","v2");
        System.out.println(jedis.get("k2"));
    }
}
