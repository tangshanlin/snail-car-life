package com.woniu.car.order.web.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 订单发票表
 * </p>
 *
 * @author WP
 * @since 2021-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_order_invoice")
@ApiModel(value="OrderInvoice对象", description="订单发票表")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInvoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单发票表id")
    @TableId(value = "invoice_id", type = IdType.AUTO)
    private Integer invoiceId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    @ApiModelProperty(value = "发票抬头名称")
    private String invoiceTitle;

    @ApiModelProperty(value = "发票抬头内容")
    private String invoiceContent;

    @ApiModelProperty(value = "发票金额")
    private BigDecimal invoiceAmount;

    @ApiModelProperty(value = "申请时间")
    private Long applicationTime;

    @ApiModelProperty(value = "开票时间")
    private Long createdTime;


}
