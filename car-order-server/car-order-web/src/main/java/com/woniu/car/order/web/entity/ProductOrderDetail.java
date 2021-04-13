package com.woniu.car.order.web.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 商品详情表
 * </p>
 *
 * @author WP
 * @since 2021-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_product_order_detail")
@ApiModel(value="ProductOrderDetail对象", description="商品详情表")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品订单详情表")
    @TableId(value = "product_order_detail_id", type = IdType.AUTO)
    private Integer productOrderDetailId;

    @ApiModelProperty(value = "订单单号")
    private String productOrderNo;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品图片（选第一张）")
    private String productImage;

    @ApiModelProperty(value = "商品数量")
    private Integer productCount;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "商品id")
    private Integer productId;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "买家是否已经评价(1表示已评价，0表示未评价)")
    private Integer buyerEate;

    @ApiModelProperty(value = "逻辑删除(0,不删，1.删除)")
    @TableLogic
    private Integer deleted;


}
