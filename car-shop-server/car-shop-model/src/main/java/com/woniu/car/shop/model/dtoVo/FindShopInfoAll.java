package com.woniu.car.shop.model.dtoVo;

import lombok.Data;

@Data
public class FindShopInfoAll {
    private Integer shopId;//门店id
    private String shopName;//门店名称
    private String shopImage;//门店图片
    private String shopLngLat;//经纬度(json)
    private String shopAddress;//门店地址
    private Integer shopOrderNumber;//总订单数（根据总成交单数计算）
    private Double shopGrade;//总评分（从成交总评分/总成交数）
    private String shopTag;//门店标签（json）
}
