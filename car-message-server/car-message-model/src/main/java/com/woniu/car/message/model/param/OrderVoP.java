package com.woniu.car.message.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@ApiModel(value = "添加订单编号信息")
public class OrderVoP implements Serializable {

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    @NotEmpty(message="订单编号不能为空")
    private String orderNo;


}
