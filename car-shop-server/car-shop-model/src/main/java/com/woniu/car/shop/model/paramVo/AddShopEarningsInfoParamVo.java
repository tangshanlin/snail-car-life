package com.woniu.car.shop.model.paramVo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/12/17:55
 * @Description:
 */
@Data
public class AddShopEarningsInfoParamVo {
    private Integer shopId;//关联门店id

    private String carServiceName;//服务名称

    private BigDecimal carServiceMoney;//服务金额

    private Long payTime;//付款时间

    private BigDecimal couponMoney;//优惠券面额(元)

    private Integer couponGoods;//发行来源(0平台-其他对应门店id)
}
