package com.woniu.car.commons.web.swagger.config;

import com.woniu.car.commons.core.code.ConstDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;


/**
 * @Author Lints
 * @Date 2021/4/6/006 13:10
 * @Description  Swagger配置类
 * @Since version-1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private SwaggerProperties sp;

    @Bean
    public Docket docket(){
        List<Parameter> ps = Arrays.asList(new ParameterBuilder().required(false)
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .name(ConstDate.REQUEST_HEADER_TOKEN)
                .description("Token参数")
                .build());
        ApiInfo af = new ApiInfoBuilder()
                .contact(new Contact(sp.getContact().getName(), sp.getContact().getHost(), sp.getContact().getEmail()))
                .description(sp.getDescription())
                .title(sp.getTitle())
                .version(sp.getVersion())
                .build();
        Docket docket=new Docket(DocumentationType.SWAGGER_2);
        return docket.apiInfo(af)
                .select()
                .apis(RequestHandlerSelectors.basePackage(sp.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(ps);
    }
}
