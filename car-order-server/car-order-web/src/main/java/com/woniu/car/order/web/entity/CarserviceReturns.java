package com.woniu.car.order.web.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 服务退款表
 * </p>
 *
 * @author WP
 * @since 2021-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_carservice_returns")
@ApiModel(value="CarserviceReturns对象", description="服务退款表")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarserviceReturns implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "服务退款表id")
    @TableId(value = "carservice_order_return_id", type = IdType.AUTO)
    private Integer carserviceOrderReturnId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "门店id")
    private Integer shopId;

    @ApiModelProperty(value = "订单编号")
    private String carserviceOrderNo;

    @ApiModelProperty(value = "退款申请时间")
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "退款处理时间")
    private Long handlingTime;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal returnsAmount;

    @ApiModelProperty(value = "退货原因")
    private String returnReason;

    @ApiModelProperty(value = "处理人")
    private String dealPerson;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;


}
