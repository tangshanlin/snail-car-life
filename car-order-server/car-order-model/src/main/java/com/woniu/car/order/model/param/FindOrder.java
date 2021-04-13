package com.woniu.car.order.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName FindOrder
 * @Desc TODO
 * @Author 21174
 * @Date 2021/4/13 16:34
 * @Version 1.0
 */
@Data
public class FindOrder {
    @ApiModelProperty(value = "订单编号")
    @NotNull(message = "订单编号不能为空")
    private String orderNo;
}
