package com.woniu.car.marketing.model.paramVo;

import lombok.Data;
/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/09/14:23
 * @Description: 支付完成后要修改优惠券数据需要调用的参数param
 */
@Data
public class UpdatePaySuccessCouponParamVo {
    private Integer couponId;//优惠券主键id
    private Integer couponInfoId;//用户优惠券详情id
}
