package com.woniu.car.order.web.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品订单物流表
 * </p>
 *
 * @author WP
 * @since 2021-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_order_logistics")
@ApiModel(value="OrderLogistics对象", description="商品订单物流表")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderLogistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物流id")
    @TableId(value = "orderlogistics_id", type = IdType.AUTO)
    private Integer orderlogisticsId;

    @ApiModelProperty(value = "商品订单表id")
    private Integer productOrderId;

    @ApiModelProperty(value = "发货快递单号")
    private String expressNo;

    @ApiModelProperty(value = "收货人姓名")
    private String consigneeRealname;

    @ApiModelProperty(value = "联系电话")
    private String consigneeTelphone;

    @ApiModelProperty(value = "收货地址")
    private String consigneeAddress;

    @ApiModelProperty(value = "物流发货运费")
    private BigDecimal logisticsFee;

    @ApiModelProperty(value = "物流成本金额")
    private BigDecimal deliveryAmount;


}
