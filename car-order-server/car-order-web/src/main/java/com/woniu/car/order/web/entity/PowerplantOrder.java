package com.woniu.car.order.web.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 电站订单表
 * </p>
 *
 * @author WP
 * @since 2021-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_powerplant_order")
@ApiModel(value="PowerplantOrder对象", description="电站订单表")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PowerplantOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "电桩订单id")
    @TableId(value = "powerplant_order_id", type = IdType.AUTO)
    private Integer powerplantOrderId;

    @ApiModelProperty(value = "电桩id")
    private Integer stationId;

    @ApiModelProperty(value = "电站id")
    private Integer powerplantId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "充电桩编号")
    private String stationNumeration;

    @ApiModelProperty(value = "充电开始时间")
    private Long chargeStartTime;

    @ApiModelProperty(value = "充电结束时间")
    private Long chargeEndTime;

    @ApiModelProperty(value = "每度电多少钱")
    private BigDecimal kilowattHourPrice;

    @ApiModelProperty(value = "电的度数")
    private Double electricity;

    @ApiModelProperty(value = "充电的费用")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "车牌号")
    private String carCode;

    @ApiModelProperty(value = "充电类型")
    private String stationType;

    @ApiModelProperty(value = "订单状态")
    private String powerplantOrderStatus;

    @ApiModelProperty(value = "买家是否已经评价(1表示已评价，0表示未评价)")
    private Integer buyerEate;

    @ApiModelProperty(value = "电桩图片")
    private String stationImage;

    @ApiModelProperty(value = "优惠券id")
    private Integer couponInfoId;

    @ApiModelProperty(value = "优惠卷面额")
    private BigDecimal couponMoney;

    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "逻辑删除(0,不删，1.删除)")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "实付金额")
    private BigDecimal amountPaid;

    @ApiModelProperty(value = "电站名称")
    private String powerplanName;

    @ApiModelProperty(value = "电桩品牌")
    private String stationBrand;




}
