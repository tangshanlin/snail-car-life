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
 * @Date: 2021/04/09/14:23
 * @Description: 支付完成后要修改优惠券数据需要调用的参数param
 */
@Data
@ApiModel(value = "支付完成后要修改优惠券数据需要调用的参数")
public class UpdatePaySuccessCouponParamVo {

    @ApiModelProperty(value = "优惠券主键id")
    @NotNull(message = "优惠券主键id不能为空")
    @Min(value = 1,message = "优惠券主键id必须大于等于1")
    private Integer couponId;//优惠券主键id

    @ApiModelProperty(value = "用户优惠券详情id")
    @NotNull(message = "用户优惠券详情id不能为空")
    @Min(value = 1,message = "用户优惠券详情id必须大于等于1")
    private Integer couponInfoId;//用户优惠券详情id
}
