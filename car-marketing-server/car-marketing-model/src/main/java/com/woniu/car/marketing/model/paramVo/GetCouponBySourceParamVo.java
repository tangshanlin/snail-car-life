package com.woniu.car.marketing.model.paramVo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/09/12:02
 * @Description: 门店要展示自己的优惠券需要传入的门店id
 */
@Data
public class GetCouponBySourceParamVo {
    private Integer couponGoods;//发行来源(0平台-其他对应门店id)
}
