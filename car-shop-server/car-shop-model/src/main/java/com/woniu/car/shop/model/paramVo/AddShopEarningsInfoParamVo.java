package com.woniu.car.shop.model.paramVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/12/17:55
 * @Description: 门店完成服务新增门店服务具体收益数据需要传入的参数
 */
@Data
@ApiModel(value = "门店完成服务新增门店服务具体收益数据需要传入的参数")
public class AddShopEarningsInfoParamVo {

    @ApiModelProperty(value = "门店id",example = "1")
    @Min(value = 1,message = "门店id必须大于等于1")
    @NotNull(message = "门店id不能为空")
    private Integer shopId;//关联门店id

    @ApiModelProperty(value = "服务名称")
    @NotNull(message = "服务名称不能为空")
    private String carServiceName;//服务名称

    @ApiModelProperty(value = "服务金额")
    @Min(value = 1,message = "服务金额必须大于等于1")
    @NotNull(message = "服务金额不能为空")
    private BigDecimal carServiceMoney;//服务金额

    @ApiModelProperty(value = "付款时间")
    @Future(message = "必须是一个将来的日期")
    private Long payTime;//付款时间

    @ApiModelProperty(value = "优惠券类别id")
    @Min(value = 1,message = "优惠券类别id必须大于等于1")
    @NotNull(message = "优惠券类别id不能为空")
    private Integer couponId;
}
