package com.woniu.car.product.model.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShowProductDto {
    private Integer productId;
    private BigDecimal productPrice;
    private String productName;
    private String productDetail;
    private JSONObject cateImages;
}
