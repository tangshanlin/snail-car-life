package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "删除单个地址的参数")
public class deleteAddress {
    @ApiModelProperty(value = "地址id",example = "1")
    @NotNull(message = "地址Id不能为空")
    Integer addressId;

}
