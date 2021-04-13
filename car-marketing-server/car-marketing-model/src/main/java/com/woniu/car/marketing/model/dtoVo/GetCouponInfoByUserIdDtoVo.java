package com.woniu.car.marketing.model.dtoVo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/12/11:43
 * @Description: 根据用户id返回所有未失效的优惠券
 */
@Data
public class GetCouponInfoByUserIdDtoVo {
    private String couponName;//优惠券名称

    private String couponInfo;//优惠券说明

    private BigDecimal couponMoney;//优惠券面额(元)

    private BigDecimal couponCondition;//使用门槛（满多少可用）
}
