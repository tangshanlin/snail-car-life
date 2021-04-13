package com.woniu.car.shop.model.dtoVo;

import lombok.Data;

@Data
public class FindShopByClassDtoVo {
    private Integer shopId;//门店id
    private String shopName;//门店名称
    private String shopImage;//门店图片
    private Integer shopOrderNumber;//总订单数
    private Integer shopGrade;//总评分
    private String shopLngLat;//经纬度(json)
    private String shopAddress;//门店地址
}
