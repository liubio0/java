package com.liuzm.redisdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class RedisDemoApplication {
    static String lockKey = "666";
    static String counterKey = "999";

    public static void main(String[] args) {
        SpringApplication.run(RedisDemoApplication.class, args);
        testRedisLock();
    }

    public static void testRedisLock(){
        LockParam lockParam = new LockParam(lockKey);
        lockParam.setTryLockTime(1000*2L);
        lockParam.setHoldLockTime(1000*10L);
        RedisLock redisLock = new RedisLock(lockParam);
        try{
            Boolean lockSucc = redisLock.lock();
            log.info("加锁结果：{}", lockSucc);
            if (lockSucc){
                try{
                    testRedisCounter();
                    Thread.sleep(1000*5L);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            log.info("redisLock error ----> ",e);
        } finally {
            boolean unlockSucc = redisLock.unlock();
            log.info("释放锁结果:{}", unlockSucc);
        }
    }

    public static void testRedisCounter(){
        RedisCounter redisCounter = new RedisCounter(counterKey, 0,1);
        for (int i = 0; i < 100; i++) {
            redisCounter.counter();
        }
        log.info("计数结果：{}", redisCounter.getCurrentValue());
    }

}
