package com.woniu.car.commons.core.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 支付使用Vo
 */
@Data
public class PayParam {

    private String out_trade_no; // 商户订单号 必填
    private String subject; // 订单名称 必填
    private BigDecimal total_amount;  // 付款金额 必填
    private String body; // 商品描述 可空
}