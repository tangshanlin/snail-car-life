package com.woniu.car;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName StartService
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/10 14:44
 * @Version 1.0
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients
@MapperScan("com.woniu.car.service.web.mapper")
public class StartService {
        public static void main(String[] args) {
            SpringApplication.run(StartService.class);
        }
}
