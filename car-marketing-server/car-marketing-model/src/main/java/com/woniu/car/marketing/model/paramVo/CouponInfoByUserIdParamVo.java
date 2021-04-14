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
 * @Date: 2021/04/12/11:39
 * @Description: 传入用户id
 */
@Data
@ApiModel(value = "根据用户id查询未过期的优惠券信息")
public class CouponInfoByUserIdParamVo {

    @NotNull(message = "用户id不能为空")
    @Min(value = 1,message = "用户id值必须大于等于1")
    @ApiModelProperty(value = "用户id")
    private Integer couponInfoUserId;//关联用户id
}
