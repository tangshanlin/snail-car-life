package com.woniu.car.items.model.param.carservice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateCarServiceSoldParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/12 11:28
 * @Version 1.0
 */
@Data
public class UpdateCarServiceSoldParam {
    @NotNull(message = "carServiceId不能为空")
    @ApiModelProperty(value = "要修改已售数量的服务id")
    private Integer carServiceId;
}
