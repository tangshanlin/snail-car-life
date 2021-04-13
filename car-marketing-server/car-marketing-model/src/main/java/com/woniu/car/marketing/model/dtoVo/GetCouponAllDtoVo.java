package com.woniu.car.marketing.model.dtoVo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 给后台调用查看所有未失效优惠券类别的model
 */
@Data
public class GetCouponAllDtoVo {
    private Integer couponId;//优惠券主键id

    private String couponName;//优惠券名称

    private String couponInfo;//优惠券说明

    private BigDecimal couponMoney;//优惠券面额(元)

    private Integer couponCondition;//使用门槛（满多少可用）

    private Integer couponGoods;//发行来源(0平台-其他对应门店id)

    private Integer couponNumber;//总发行量

    private Long couponEndTime;//生效时间

    private Long couponBeginTime;//失效时间

    private Integer couponGetNumber;//已领取数量

    private Integer couponNoGetNumber;//待领取数量

    private Integer couponUseNumber;//已使用数量

    private Integer couponNoUseNumber;//未使用数量
}
