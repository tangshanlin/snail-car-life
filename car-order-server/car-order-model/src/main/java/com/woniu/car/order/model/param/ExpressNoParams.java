package com.woniu.car.order.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    private Integer productOrderId;

    @ApiModelProperty(value = "发货快递单号")
    private String expressNo;

}
