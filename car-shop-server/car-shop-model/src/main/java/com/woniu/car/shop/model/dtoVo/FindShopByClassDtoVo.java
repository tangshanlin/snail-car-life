package com.woniu.car.shop.model.dtoVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FindShopByClassDtoVo {
    private Integer shopId;//门店id
    private String shopName;//门店名称
    private String shopImage;//门店图片
    private Integer shopOrderNumber;//总订单数
    private Integer shopGrade;//总评分
    private String shopLongitude;//经度
    private String shopLatitude;//纬度
    private String shopAddress;//门店地址
}
