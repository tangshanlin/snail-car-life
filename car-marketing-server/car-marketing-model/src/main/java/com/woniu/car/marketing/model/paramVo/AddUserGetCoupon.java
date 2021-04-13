package com.woniu.car.marketing.model.paramVo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/09/9:59
 * @Description: 用户领取优惠券传入的参数param
 */
@Data
public class AddUserGetCoupon {

    private Integer couponId;//关联优惠卷表id

    private Integer couponInfoUserId;//关联用户id
}
