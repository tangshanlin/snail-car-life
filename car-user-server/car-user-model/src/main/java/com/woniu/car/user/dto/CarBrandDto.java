package com.woniu.car.user.dto;

import com.woniu.car.user.web.domain.CarBrand;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CarBrandDto
 * @Desc TODO
 * @Author Administrator
 * @Date 2021/4/14 17:34
 * @Version 1.0
 */
@Data
public class CarBrandDto {
    String key;
    List<CarBrand> value;



}
