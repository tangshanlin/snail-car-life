package com.woniu.car;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.woniu.car.shop.web.mapper")
@EnableFeignClients
public class ShopRunStart {
    public static void main(String[] args) {
        SpringApplication.run(ShopRunStart.class,args);
    }
}
