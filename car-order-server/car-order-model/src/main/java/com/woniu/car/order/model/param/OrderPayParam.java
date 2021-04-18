package com.woniu.car.order.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
    @NotEmpty(message = "订单编号不能为空")
    private String orderNo;

    @ApiModelProperty(value = "订单支付渠道(1.钱包密码，2.支付宝)")
    @NotEmpty(message = "支付方式不能为空")
    private String payChannel;

    @ApiModelProperty(value = "钱包支付密码")
    private String walletPassword;
}
