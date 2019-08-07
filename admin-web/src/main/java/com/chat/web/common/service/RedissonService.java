package com.chat.web.common.service;

import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author franky
 * @description Redisson 操作实现类
 * @date 2019/1/13
 */
@Service
public class RedissonService {
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 获取锁
     * @param objectName
     * @return
     */
    public RLock getRLock(String objectName){
        RLock rLock = redissonClient.getLock(objectName);
        return rLock;
    }

    /**
     * 获取读取锁
     * @param objectName
     * @return
     */
    public RReadWriteLock getReadWriteLock(String objectName){
        RReadWriteLock rReadWriteLock = redissonClient.getReadWriteLock(objectName);
        return rReadWriteLock;
    }

    /**
     * 获取原子数
     * @param objectName
     * @return
     */
    public RAtomicLong getRAtomicLong(String objectName){
        RAtomicLong rAtomicLong = redissonClient.getAtomicLong(objectName);
        return rAtomicLong;
    }

    /**
     * 获取计数锁
     * @param objectName
     * @return
     */
    public RCountDownLatch getRCountDownLatch(String objectName){
        RCountDownLatch rCountDownLatch = redissonClient.getCountDownLatch(objectName);
        return rCountDownLatch;
    }


}
