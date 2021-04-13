package com.woniu.car;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.woniu.car.user.web.mapper")
@EnableFeignClients
public class UserRunStarter {
    public static void main(String[] args) {
        SpringApplication.run(UserRunStarter.class,args);
    }
}
