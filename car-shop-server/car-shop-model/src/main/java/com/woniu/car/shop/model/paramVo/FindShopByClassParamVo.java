package com.woniu.car.shop.model.paramVo;

import lombok.Data;

@Data
public class FindShopByClassParamVo {
    private Integer shopClass;//所属类型（0非4s 1是4s店）
    private String shopBrand;//所属品牌
}
