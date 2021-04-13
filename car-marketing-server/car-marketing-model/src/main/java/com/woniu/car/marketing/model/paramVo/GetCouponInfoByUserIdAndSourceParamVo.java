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
 * @Description: 查询某个订单对应所有可用优惠券需要的参数
 */
@Data
@ApiModel(value = "查询某个订单对应所有可用优惠券需要的参数")
public class GetCouponInfoByUserIdAndSourceParamVo {

    @NotNull(message = "发行来源不能为空")
    @Min(value = 0,message = "发行来源大于等于0")
    @ApiModelProperty(value = "发行来源(0平台-其他对应门店id)")
    private Integer couponGoods;

    /*@NotNull(message = "关联用户id不能为空")
    @Min(value = 1,message = "用户id大于等于0")
    @ApiModelProperty(value = "关联用户id")
    private Integer couponInfoUserId;//关联用户id*/
}
