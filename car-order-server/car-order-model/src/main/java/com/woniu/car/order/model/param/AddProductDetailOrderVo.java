package com.woniu.car.order.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName AddProductDetailOrder
 * @Desc TODO 订单详细信息
 * @Author 21174
 * @Date 2021/4/8 1:28
 * @Version 1.0
 */
@Data
public class AddProductDetailOrderVo {

    @ApiModelProperty(value = "商品数量")
    @NotNull(message = "商品数量不能为空")
    private Integer productCount;

    @ApiModelProperty(value = "商品id")
    @NotNull(message = "商品id不能为空")
    private Integer productId;
}
