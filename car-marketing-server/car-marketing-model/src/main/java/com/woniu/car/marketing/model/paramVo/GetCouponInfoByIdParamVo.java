package com.woniu.car.marketing.model.paramVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/09/12:02
 * @Description: 用户在订单中确定使用某张优惠券需要传入的参数param
 */
@Data
@ApiModel(value = "用户在订单中确定使用某张优惠券需要传入的用户领取优惠券主键id参数")
public class GetCouponInfoByIdParamVo {

    @NotNull(message = "用户领取优惠券表主键id不能为空")
    @Min(value = 0,message = "用户某个优惠券id必须大于等于0")
    @ApiModelProperty(value = "关联用户某个优惠券id")
    private Integer couponInfoId;
}
