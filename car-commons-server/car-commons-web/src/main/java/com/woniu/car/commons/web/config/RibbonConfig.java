package com.woniu.car.commons.web.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;

public class RibbonConfig {

    @Bean
    public IRule iRule(){
        return new RandomRule();
    }


}
