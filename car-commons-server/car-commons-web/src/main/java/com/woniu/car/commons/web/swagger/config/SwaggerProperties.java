package com.woniu.car.commons.web.swagger.config;

import com.woniu.car.commons.web.swagger.bean.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Lints
 * @Date 2021/4/6/006 13:10
 * @Description 配置类需要提供的参数信息
 * @Since version-1.0
 */

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    private String title;
    private String version;
    private String basePackage;
    private String description;
    private Contact contact;
}
