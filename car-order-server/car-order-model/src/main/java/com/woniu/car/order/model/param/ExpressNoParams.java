package com.woniu.car.order.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName UpOrderStatusForSent
 * @Desc TODO 修改商品订单发货时用
 * @Author 21174
 * @Date 2021/4/11 10:34
 * @Version 1.0
 */
@Data
public class ExpressNoParams {

    @ApiModelProperty(value = "商品订单表id")
    @NotNull(message = "商品订单表id不能为空")
    private Integer productOrderId;

    @ApiModelProperty(value = "发货快递单号")
    @NotEmpty(message = "发货快递单号不能为空")
    private String expressNo;

}
