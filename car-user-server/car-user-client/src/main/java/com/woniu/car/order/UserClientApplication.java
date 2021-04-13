package com.woniu.car.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class OrderClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderClientApplication.class,args);

    }
}
