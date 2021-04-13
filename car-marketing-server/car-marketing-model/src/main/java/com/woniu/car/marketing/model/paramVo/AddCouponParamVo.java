package com.woniu.car.marketing.model.paramVo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 添加优惠券类别填写的信息
 */
@Data
public class AddCouponParamVo {
    private String couponName;//优惠券名称
    private String couponInfo;//优惠券说明
    private BigDecimal couponMoney;//优惠券面额(元)
    private Integer couponCondition;//使用门槛（满多少可用）
    private Integer couponGoods;//发行来源(0平台-其他对应门店id)
    private Integer couponNumber;//总发行量
    private Long couponBeginTime;//生效时间
    private Long couponEndTime;//失效时间
}
