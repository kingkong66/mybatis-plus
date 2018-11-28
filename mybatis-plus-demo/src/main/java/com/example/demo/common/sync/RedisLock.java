package com.example.demo.common.sync;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by Tai on 2018/11/23.
 */
@Service
@Slf4j
public class RedisLock implements Lock{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String REDIS_LOCK_KEY = "redis_lock_key";

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    @Override
    public void lock() {
        if(tryLock()){
            try{
                Thread.sleep(1000l);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        lock();
    }

    @Override
    public boolean tryLock() {
        String uuid = UUID.randomUUID().toString();
       boolean ret = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK_KEY,uuid);
       if(ret){
           threadLocal.set(uuid);
           return true;
       }
        return false;
    }

    @Override
    public void unlock() {
        String lockValue = stringRedisTemplate.opsForValue().get(REDIS_LOCK_KEY);
        if(StringUtils.isNotEmpty(lockValue) && threadLocal.get().equals(lockValue)){
            stringRedisTemplate.delete(REDIS_LOCK_KEY);
        }

    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long l, TimeUnit timeUnit) throws InterruptedException {
        return false;
    }
}
