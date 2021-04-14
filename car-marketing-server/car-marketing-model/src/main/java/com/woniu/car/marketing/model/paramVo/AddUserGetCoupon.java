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
 * @Date: 2021/04/09/9:59
 * @Description: 用户领取优惠券传入的参数param
 */
@Data
@ApiModel(value = "用户领取优惠券传入的参数")
public class AddUserGetCoupon {

    @NotNull(message = "优惠卷表id不能为空")
    @Min(value = 0,message = "优惠卷表id必须大于等于0")
    @ApiModelProperty(value = "优惠卷表id值")
    private Integer couponId;

    @NotNull(message = "用户id不能为空")
    @Min(value = 1,message = "用户id值必须大于等于1")
    @ApiModelProperty(value = "用户id")
    private Integer couponInfoUserId;
}
