package com.woniu.car;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.woniu.car.station.web.mapper")
public class stationstart {
    public static void main(String[] args) {
        SpringApplication.run(stationstart.class);
    }
}
