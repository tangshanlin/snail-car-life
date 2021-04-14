package com.woniu.car.order.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/*
 * @Author WangPeng
 * @Description 新增订单类
 * @Date  2021/4/8
 * @Param 
 * @return 
 **/
@Data
public class AddCarServiceOrderVo {
    @ApiModelProperty(value = "优惠券id")
    @NotNull(message = "优惠券id不能为空")
    private Integer couponInfoId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "门店id")
    @NotNull(message = "门店id不能为空")
    private Integer shopId;

    @ApiModelProperty(value = "服务id")
    @NotNull(message = "服务id不能为空")
    private Integer carserviceId;

    @ApiModelProperty(value = "预约时间")
    @NotNull(message = "预约时间不能为空")
    private Long appointTime;




}
