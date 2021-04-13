package com.woniu.car.marketing.model.dtoVo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 在订单中展示符合条件的所有优惠券
 */
@Data
public class GetCouponInfoByIdDtoVo {
    private Integer couponInfoId;//用户的优惠券详情id

    private Integer couponId;//优惠券主键id

    private String couponName;//优惠券名称

    private BigDecimal couponMoney;//优惠券面额(元)

    private BigDecimal couponCondition;//使用门槛（满多少可用）

}
