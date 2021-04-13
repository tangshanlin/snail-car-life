package com.woniu.car.product.model.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 二级分类查出的商品列表Dto
 */
@Data
public class ProductDtoTwo {
    private Integer productId;
    private String productName;
    private BigDecimal productPrice;
    private JSONObject cateImages;
}
