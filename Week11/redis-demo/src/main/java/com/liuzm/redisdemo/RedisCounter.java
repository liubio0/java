package com.liuzm.redisdemo;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;
import java.util.UUID;

@Slf4j
public class RedisCounter {
    //1）计数增长
    //2）获取当前值
    //3）计数归零

    //计数器前缀
    private final static String prefix_counter = "redisCounter";
    //计数增长的脚本（如果不存在就设置初始值，默认为1）
    private final static String incrScript = "if redis.call('get',KEYS[1]) then " +
            "return redis.call('incrby',KEYS[1],ARGV[1]) else return redis.call('set',KEYS[1],ARGV[2]) end";
    //计数递减的脚本（如果<=0就返回-1，如果不存在就设置初始值，默认为1000）
    private final static String decrScript = "if redis.call('get',KEYS[1]) then " +
            " if redis.call('get',KEYS[1]) > 0 then return redis.call('decrby',KEYS[1],ARGV[1]) else return -1 end " +
            " else return redis.call('set',KEYS[1],ARGV[2]) end";
    private final static Long counterSuccesss = 1L;

    private String redisCounterKey;

    //递增/递减的步长默认为1
    private Integer stepLength = 1;
    private Integer beginValue = 1;

    private Jedis jedis;
    private Jedis getJedis(){
        return this.jedis;
    }
    private void closeJedis(Jedis jedis){
        jedis.close();
        jedis = null;
    }

    public RedisCounter(String counterKey, int beginValue, int stepLength){
        this.redisCounterKey = prefix_counter.concat(counterKey);
        this.beginValue = beginValue;
        this.stepLength = stepLength;

        jedis = new Jedis("192.168.2.88",6379);
    }

    public String getCurrentValue(){
        return getJedis().get(this.redisCounterKey);
    }

    public boolean counter(){
        if(this.stepLength < 0){
            return decr();
        }
        else{
            return incr();
        }
    }

    public boolean incr(){
        Object result = null;
        try{
             result = getJedis().eval(incrScript, Collections.singletonList(redisCounterKey), Collections.singletonList(stepLength.toString()));
                if (counterSuccesss.equals(result)){
                    return true;
                }
        } catch (Exception e){
            log.warn("incr failure redisCounterKey:{} redisCounterStepLength:{} result:{}",redisCounterKey,stepLength,result,e);
        } finally{
            this.closeJedis(jedis);
        }
        return false;
    }

    public boolean decr(){
        Object result = null;
        try{
            result = getJedis().eval(decrScript, Collections.singletonList(redisCounterKey), Collections.singletonList(stepLength.toString()));
            if (counterSuccesss.equals(result)){
                return true;
            }
        } catch (Exception e){
            log.warn("incr failure redisCounterKey:{} redisCounterStepLength:{} result:{}",redisCounterKey,stepLength,result,e);
        } finally{
            this.closeJedis(jedis);
        }
        return false;
    }
}
