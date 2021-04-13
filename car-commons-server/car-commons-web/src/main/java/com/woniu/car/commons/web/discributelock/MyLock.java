package com.woniu.car.commons.web.discributelock;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLock {

    /**
     * 分布所注解属性key值一般用的是包名
     *
     */
    String key();

    /**
     * 获取锁的时间
     *
     */

    int timeoutGain() default 10;
    /**
     * 锁失效的时间
     *
     */
    int timeoutLive() default 15;

    /**
     * 分布所注解属性key值一般用的是对象类的id值
     *
     */
    String methodParam() default "";

}
