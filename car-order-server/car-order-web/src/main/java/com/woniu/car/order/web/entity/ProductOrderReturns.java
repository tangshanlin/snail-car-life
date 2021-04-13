package com.woniu.car.order.web.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 商品退货表
 * </p>
 *
 * @author WP
 * @since 2021-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_product_order_returns")
@ApiModel(value="ProductOrderReturns对象", description="商品退货表")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderReturns implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "退货id")
    @TableId(value = "order_returns_id", type = IdType.AUTO)
    private Integer orderReturnsId;

    @ApiModelProperty(value = "退货编号")
    private String returnsNo;

    @ApiModelProperty(value = "订单id")
    private String productOrderId;

    @ApiModelProperty(value = "用户id")
    private Integer UserId;

    @ApiModelProperty(value = "物流单号")
    private String expressNo;

    @ApiModelProperty(value = "收货人姓名")
    private String consigneeRealname;

    @ApiModelProperty(value = "联系电话")
    private String consigneeTelphone;

    @ApiModelProperty(value = "收货地址")
    private String consigneeAddress;

    @ApiModelProperty(value = "邮政编码")
    private Integer consigneeZip;

    @ApiModelProperty(value = "退货类型")
    private String returnsType;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal returnsAmount;

    @ApiModelProperty(value = "退货申请时间")
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "退货处理时间")
    private Long handlingTime;

    @ApiModelProperty(value = "退货原因")
    private String returnReason;

    @ApiModelProperty(value = "删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "物流状态（待发货，已发货，已收货）")
    private String deliveryStatus;

    @ApiModelProperty(value = "处理人(平台)")
    private String dealPerson;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "逻辑删除(0,不删，1.删除)")
    private Integer deleted2;

}
