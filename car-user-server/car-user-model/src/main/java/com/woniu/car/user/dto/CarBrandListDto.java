package com.woniu.car.user.dto;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * @ClassName CarBrandListDto
 * @Desc TODO
 * @Author Administrator
 * @Date 2021/4/14 17:34
 * @Version 1.0
 */
@Data
@Document(indexName = "carbrand")
public class CarBrandListDto {
    @Id
    @Field(type = FieldType.Keyword,name = "key")
    String key;
    List<CarBrandDto> value;



}
