package com.liuzm.redisdemo;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;
import java.util.UUID;

@Slf4j
public class RedisLock {
    //锁前缀
    private final static String prefix_key = "redisLock";
    //释放锁的lua脚本
    private final static String unlockScript = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
            "return redis.call('del',KEYS[1]) else return 0 end";
    //释放锁脚本执行成功值
    private final static Long unlockSuccesss = 1L;

    private LockParam lockParam;
    private Long tryLockEndTime;
    private String redisLockKey;
    private String redisLockValue;
    private Boolean holdLockSuccess = Boolean.FALSE;

    private Jedis jedis;
    private Jedis getJedis(){
        return this.jedis;
    }
    private void closeJedis(Jedis jedis){
        jedis.close();
        jedis = null;
    }

    public RedisLock(LockParam lockParam){
        if (lockParam == null) {
            new RuntimeException("lockParam is null");
        }
        if (lockParam.getLockKey() == null || lockParam.getLockKey().trim().length() == 0) {
            new RuntimeException("lockParam lockKey is error");
        }
        this.lockParam = lockParam;
        this.tryLockEndTime = lockParam.getTryLockTime() + System.currentTimeMillis();
        this.redisLockKey = prefix_key.concat(lockParam.getLockKey());
        this.redisLockValue = UUID.randomUUID().toString().replaceAll("-", "");

        jedis = new Jedis("192.168.2.88",6379);
    }

    public boolean lock(){
        //在尝试获取锁的时间范围内，每50毫秒尝试一次获取锁，直至获取到锁或者超时。
        while(true){
            if(System.currentTimeMillis() > tryLockEndTime){
                return false;
            }
            holdLockSuccess = tryLock();
            if(Boolean.TRUE.equals(holdLockSuccess)){
                return true;
            }

            try{
                Thread.sleep(50);
            } catch ( InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public boolean tryLock(){
        try{
            SetParams setParams = new SetParams();
            setParams.nx();
            setParams.px(lockParam.getHoldLockTime());
            String result = getJedis().set(redisLockKey,redisLockValue,setParams);
            if ("OK".equals(result)) {
                return true;
            }
        }catch (Exception e){
            log.warn("tryLock failure redisLockKey:{} redisLockValue:{} lockParam:{}", redisLockKey,redisLockValue,lockParam,e);
        }
        return false;
    }

    public Boolean unlock(){
        Object result = null;
        try{
            if(Boolean.TRUE.equals(holdLockSuccess)){
                result = getJedis().eval(unlockScript, Collections.singletonList(redisLockKey), Collections.singletonList(redisLockValue));
                if (unlockSuccesss.equals(result)){
                    return true;
                }
            }
        } catch (Exception e){
            log.warn("unlock failure redisLockKey:{} redisLockValue:{} lockParam:{} result:{}",redisLockKey,redisLockValue,lockParam,result,e);
        } finally{
            this.closeJedis(jedis);
        }
        return false;
    }
}
