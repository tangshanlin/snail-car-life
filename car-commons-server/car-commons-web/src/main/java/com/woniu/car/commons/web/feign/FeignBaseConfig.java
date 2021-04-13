package com.woniu.car.commons.web.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class FeignBaseConfig {

    /**
    feign 的配置
     */

    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }
}
