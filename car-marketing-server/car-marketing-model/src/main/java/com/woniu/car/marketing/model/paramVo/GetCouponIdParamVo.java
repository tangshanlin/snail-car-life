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
 * @Date: 2021/04/10/0:49
 * @Description: 优惠券主键id
 */
@Data
@ApiModel(value = "传入优惠券主键id")
public class GetCouponIdParamVo {
    @ApiModelProperty(value = "优惠券类别id",example = "1")
    @Min(value = 1,message = "优惠券类别id必须大于等于0")
    @NotNull(message = "优惠券类别id不能为空")
    private Integer couponId;//优惠券类别id
}

