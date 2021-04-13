package com.woniu.car.product.model.parame;


import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.reflect.Member;

/**
 * Copyright (C), 2021, 温天宇
 *
 * @author WTY
 * Date: 2021/4/13 20:04
 * FileName: ProductStatusParams
 */
@Data
public class ProductStatusParams {
    @NotNull
    @ApiModelProperty(value = "上架或者下架，上架1，下架0",example = "1")
    private Integer type;
    @NotNull
    @ApiModelProperty(value = "商品id",example = "1")
    private Integer productId;
}
