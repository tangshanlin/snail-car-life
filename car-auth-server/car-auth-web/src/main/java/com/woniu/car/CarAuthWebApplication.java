package com.woniu.car;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Copyright (C), 2021, 温天宇
 *
 * @author WTY
 * Date: 2021/4/5 14:30
 * FileName: CarAuthWebApplication
 */
@SpringBootApplication
@MapperScan("com.woniu.car.auth.web.mapper")
@EnableFeignClients
public class CarAuthWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarAuthWebApplication.class,args);
    }
}
