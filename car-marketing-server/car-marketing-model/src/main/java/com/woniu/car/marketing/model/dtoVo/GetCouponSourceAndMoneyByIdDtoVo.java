package com.woniu.car.marketing.model.dtoVo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/17/1:07
 * @Description: feign调用，返回面额和来源
 */
@Data
public class GetCouponSourceAndMoneyByIdDtoVo implements Serializable {
    private BigDecimal couponMoney;//优惠券面额
    private Integer couponGoods;//优惠券来源
}
