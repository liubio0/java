package com.liuzm.redisdemo;

import lombok.Data;

@Data
public class LockParam {
    private String lockKey;
    //尝试获取锁的时间（毫秒）
    private Long tryLockTime;
    //获取锁之后持有锁的时间（毫秒）
    private Long holdLockTime;

    public LockParam(String lockKey){
        this(lockKey, 1000*3L,1000*5L);
    }

    public LockParam(String lockKey, Long tryLockTime, Long holdLockTime){
        this.lockKey = lockKey;
        this.tryLockTime = tryLockTime;
        this.holdLockTime = holdLockTime;
    }
}
