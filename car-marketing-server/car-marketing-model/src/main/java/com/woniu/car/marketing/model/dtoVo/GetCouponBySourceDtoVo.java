package com.woniu.car.marketing.model.dtoVo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/09/11:58
 * @Description: 用于查看当前门店可用的优惠券
 */
@Data
public class GetCouponBySourceDtoVo {
    private Integer couponId;//优惠券主键id

    private String couponName;//优惠券名称

    private String couponInfo;//优惠券说明

    private BigDecimal couponMoney;//优惠券面额(元)
}
