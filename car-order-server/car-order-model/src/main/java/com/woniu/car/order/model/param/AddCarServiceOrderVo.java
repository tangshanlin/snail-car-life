package com.woniu.car.order.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    private Integer couponInfoId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "门店id")
    private Integer shopId;

    @ApiModelProperty(value = "服务id")
    private Integer carserviceId;

    @ApiModelProperty(value = "预约时间")
    private Long appointTime;




}
