package com.woniu.car;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.woniu.car.message.web.mapper")
@EnableTransactionManagement
@EnableFeignClients
public class MessageApplicationRunStart {

    public static void main(String[] args) {
        SpringApplication.run(MessageApplicationRunStart.class,args);
    }

}
