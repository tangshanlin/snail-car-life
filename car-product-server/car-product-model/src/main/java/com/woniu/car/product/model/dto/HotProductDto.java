package com.woniu.car.product.model.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 热销商品Dto
 */
@Data
public class HotProductDto {
    private Integer productId;
    private String productName;
    private JSONObject productImage;
    private String productDetail;
    private BigDecimal productPrice;




}
