package com.woniu.car.order.model.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @ApiModelProperty(value = "电站订单id")
    @NotNull(message = "电站订单id不能为空")
    private Integer powerplantOrderId;

    @ApiModelProperty(value = "电桩id")
    @NotNull(message = "电桩id不能为空")
    private Integer stationId;

    @ApiModelProperty(value = "电站id")
    @NotNull(message = "电站订单id不能为空")
    private Integer powerplantId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "充电开始时间")
    @NotNull(message = "充电开始时间不能为空")
    private Long chargeStartTime;

    @ApiModelProperty(value = "充电结束时间")
    @NotNull(message = "充电结束时间不能为空")
    private Long chargeEndTime;

    @ApiModelProperty(value = "电的度数")
    @NotNull(message = "点的度数不能为空")
    private Double electricity;

    @ApiModelProperty(value = "优惠券id")
    @NotNull(message = "优惠券id不能为空")
    private Integer couponInfoId;

    @ApiModelProperty(value = "车牌号")
    @NotEmpty(message = "车牌号不能为空")
    private String carCode;

}
