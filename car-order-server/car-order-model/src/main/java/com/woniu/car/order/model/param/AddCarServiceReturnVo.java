package com.woniu.car.order.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName AddCarServiceReturnVo
 * @Desc TODO 新增服务退款类
 * @Author 21174
 * @Date 2021/4/11 1:12
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class AddCarServiceReturnVo {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "订单编号")
    private String carserviceOrderNo;

    @ApiModelProperty(value = "退货原因")
    private String returnReason;
}
