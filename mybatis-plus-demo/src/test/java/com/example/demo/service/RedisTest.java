package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Demo;
import com.example.demo.entity.RedisEntry;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tai on 2018/11/21.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class RedisTest {

    public static final String REDIS_KEY = "demo:hash";

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void redisTest()throws Exception{
        String key = "test_redis";
        redisTemplate.opsForValue().set(key, "测试redis存值");
        String redisValue = redisTemplate.opsForValue().get("test_redis").toString();
        log.info("test_redis的值为："+redisValue);
        boolean flag = redisTemplate.delete("test_redis");
        log.info("redis 删除key ："+ flag);
    }

    @Test
    public void redisHashTest()throws Exception{
        List<RedisEntry> demoList =  redisEntryList();
        for(RedisEntry entry:demoList){
            redisTemplate.opsForHash().put(REDIS_KEY,entry.getId(),new Gson().toJson(entry));
        }
    }

    public static List<RedisEntry> redisEntryList(){
        List<RedisEntry> redisList = new ArrayList<>();
        RedisEntry entry1 = new RedisEntry("1","admin","add admin");
        redisList.add(entry1);
        RedisEntry entry2 = new RedisEntry("2","user","add user");
        redisList.add(entry2);
        RedisEntry entry3 = new RedisEntry("3","root","add root");
        redisList.add(entry3);
        return redisList;
    }

    @Test
    public void redisGetHash()throws Exception{
        log.info("entry的值为："+ new Gson().toJson(redisTemplate.opsForHash().get(REDIS_KEY,1)));
    }
}
