package com.woniu.car.marketing.model.paramVo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/09/12:02
 * @Description: 查询某个订单对应所有可用优惠券需要的参数
 */
@Data
public class GetCouponInfoByUserIdAndSourceParamVo {
    private Integer couponGoods;//发行来源(0平台-其他对应门店id)

    private Integer couponInfoUserId;//关联用户id
}
