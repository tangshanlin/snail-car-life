package com.woniu.car.order.web.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 服务订单表
 * </p>
 *
 * @author WP
 * @since 2021-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_carservice_order")
@ApiModel(value="CarserviceOrder对象", description="服务订单表")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarserviceOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "carservice_order_id", type = IdType.AUTO)
    private Integer carserviceOrderId;

    @ApiModelProperty(value = "优惠券id")
    private Integer couponInfoId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "门店id")
    private Integer shopId;

    @ApiModelProperty(value = "服务id")
    private Integer carserviceId;

    @ApiModelProperty(value = "优惠卷面额")
    private BigDecimal couponMoney;

    @ApiModelProperty(value = "服务名称")
    private String carserviceName;

    @ApiModelProperty(value = "服务价格")
    private BigDecimal carservicePrice;

    @ApiModelProperty(value = "订单支付渠道(1.钱包密码，2.支付宝)")
    private String payChannel;

    @ApiModelProperty(value = "实际付款金额")
    private BigDecimal cartserviceOrderAmountTotal;

    @ApiModelProperty(value = "付款时间")
    private Long payTime;

    @ApiModelProperty(value = "订单单号")
    private String carserviceOrderNo;

    @ApiModelProperty(value = "订单状态(已付款,服务进行中，已完成，退款中，已退款,取消交易)")
    private String carserviceOrderStatus;

    @ApiModelProperty(value = "买家是否已经评价(1表示已评价，0表示未评价)")
    private Integer buyerEate;

    @ApiModelProperty(value = "预约时间")
    private Long appointTime;

    @ApiModelProperty(value = "门店地址")
    private String shopAddress;

    @ApiModelProperty(value = "门店名字")
    private String shopName;

    @ApiModelProperty(value = "门店电话")
    private String shopTel;

    @ApiModelProperty(value = "订单券码")
    private String orderCode;

    @ApiModelProperty(value = "门店图片")
    private String shopImage;

    @ApiModelProperty(value = "用户电话")
    private String userTel;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "逻辑删除(0,不删，1.删除)")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "使用二维码图片")
    private String useQrCode;

//    @ApiModelProperty(value = "使用码")
//    private String useCode;

}
