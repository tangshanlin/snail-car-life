package com.woniu.car.order.web.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 商品订单表
 * </p>
 *
 * @author WP
 * @since 2021-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_product_order")
@ApiModel(value="ProductOrder对象", description="商品订单表")
//@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id")
    @TableId(value = "product_order_id", type = IdType.AUTO)
    private Integer productOrderId;

    @ApiModelProperty(value = "优惠券id")
    private Integer couponInfoId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "订单单号")
    private String productOrderNo;

    @ApiModelProperty(value = "商品总数量")
    private Integer productSum;

    @ApiModelProperty(value = "订单物流信息表id")
    private Integer orderlogisticsId;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "商品总价")
    private BigDecimal productAmountTotal;

    @ApiModelProperty(value = "订单支付渠道")
    private String payChannel;

    @ApiModelProperty(value = "实际付款金额")
    private BigDecimal productOrderAmountTotal;

    @ApiModelProperty(value = "应付金额")
    private BigDecimal amountPayable;

    @ApiModelProperty(value = "优惠卷面额")
    private BigDecimal couponMoney;

    @ApiModelProperty(value = "付款时间")
    private Long payTime;

    @ApiModelProperty(value = "发货时间")
    private Long deliverGoodsTime;

    @ApiModelProperty(value = "成交时间")
    private Long commitTime;

    @ApiModelProperty(value = "订单状态(未付款,已付款,已发货,已签收,退货中,已退货,取消交易)")
    private String productOrderStatus;

    @ApiModelProperty(value = "逻辑删除(0,不删，1.删除)")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "物流发货运费")
    private BigDecimal logisticsFee;

    //忽略字段
    @TableField(exist = false)
    private List<ProductOrderDetail> productOrderDetails;
}
