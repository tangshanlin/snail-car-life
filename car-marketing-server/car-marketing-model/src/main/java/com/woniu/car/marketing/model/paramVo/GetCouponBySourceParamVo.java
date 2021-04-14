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
 * @Description: 门店要展示自己的优惠券需要传入的门店id
 */
@Data
@ApiModel(value = "门店要展示自己的优惠券需要传入的门店id")
public class GetCouponBySourceParamVo {

    @ApiModelProperty(value = "发行来源(0平台-其他对应门店id)")
    @Min(value = 0,message = "发行来源必须大于等于0")
    @NotNull(message = "发行来源不能为空")
    private Integer couponGoods;//发行来源(0平台-其他对应门店id)
}
