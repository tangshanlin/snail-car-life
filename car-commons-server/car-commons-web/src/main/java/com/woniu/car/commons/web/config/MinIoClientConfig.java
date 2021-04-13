package com.woniu.car.commons.web.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIoClientConfig {
    /**
     * 注入minio 客户端
     * @return
     */
    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint("http://192.168.10.86:9000")
                .credentials("minioadmin", "minioadmin")
                .build();
    }

}
