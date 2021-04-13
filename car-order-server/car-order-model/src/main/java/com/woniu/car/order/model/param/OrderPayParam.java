package com.woniu.car.order.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName OrderPayParam
 * @Desc TODO
 * @Author 21174
 * @Date 2021/4/12 16:41
 * @Version 1.0
 */
@Data
public class OrderPayParam {
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "订单支付渠道(1.钱包密码，2.支付宝)")
    private String payChannel;
}
