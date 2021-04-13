package com.woniu.car.commons.web.discributelock;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class MyLockAdvice {

    @Resource
    private RedissonClient redissonClient;

    /**
     * @Author Lints
     * @Date 2021/3/22/024 23:54
     * @Description 实现分布式锁
     * @Param [pjp]
     * @Return java.lang.Object
     * @Since version-1.0
     */

    @Around("@annotation(com.woniu.car.commons.web.discributelock.MyLock)")
    public Object mylock(ProceedingJoinPoint pjp) throws Throwable{
        MethodSignature ms= (MethodSignature) pjp.getSignature();
        Method method = ms.getMethod();
        MyLock myLock= method.getDeclaredAnnotation(MyLock.class);
        String key = myLock.key();
        if(!"".equals(myLock.methodParam())){
            Object arg = pjp.getArgs()[0];
            Field field = arg.getClass().getDeclaredField(myLock.methodParam());
            field.setAccessible(true);
            String paramId = field.get(arg).toString();
            key=key+":"+paramId;
        }else if(!ObjectUtils.isEmpty(pjp.getArgs()[0])){
            key=key+":"+pjp.getArgs()[0];
        }
        RLock lock = redissonClient.getLock(key);
        try {
            lock.tryLock(myLock.timeoutGain(), myLock.timeoutLive(), TimeUnit.SECONDS);

            return pjp.proceed();
        }finally {
            lock.unlock();
        }
    }
}
