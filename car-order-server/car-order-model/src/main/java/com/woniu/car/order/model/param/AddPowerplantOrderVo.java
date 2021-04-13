package com.woniu.car.order.model.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName AddPowerplantOrder
 * @Desc TODO 生成电站订单需要的参数
 * @Author 21174
 * @Date 2021/4/8 12:12
 * @Version 1.0
 */

@Data
public class AddPowerplantOrderVo {

    @ApiModelProperty(value = "电桩订单id")
    private Integer powerplantOrderId;

    @ApiModelProperty(value = "电桩id")
    private Integer stationId;

    @ApiModelProperty(value = "电站id")
    private Integer powerplantId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "充电开始时间")
    private Long chargeStartTime;

    @ApiModelProperty(value = "充电结束时间")
    private Long chargeEndTime;

    @ApiModelProperty(value = "电的度数")
    private Double electricity;

    @ApiModelProperty(value = "优惠券id")
    private Integer couponInfoId;

    @ApiModelProperty(value = "车牌号")
    private String carCode;

}
