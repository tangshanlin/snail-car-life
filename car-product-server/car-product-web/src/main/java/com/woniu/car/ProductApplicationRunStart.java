package com.woniu.car;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.woniu.car.product.web.mapper")
public class ProductApplicationRunStart {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplicationRunStart.class,args);
    }
}
