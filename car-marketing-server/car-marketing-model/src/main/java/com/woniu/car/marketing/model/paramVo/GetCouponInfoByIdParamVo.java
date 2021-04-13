package com.woniu.car.marketing.model.paramVo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/09/12:02
 * @Description: 用户在订单中确定使用某张优惠券需要传入的参数param
 */
@Data
public class GetCouponInfoByIdParamVo {
    private Integer couponInfoId;//关联用户某个优惠券id
}
