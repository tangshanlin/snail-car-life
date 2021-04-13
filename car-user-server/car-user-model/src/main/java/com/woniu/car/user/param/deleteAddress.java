package com.woniu.car.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class deleteAddress {
    @ApiModelProperty(value = "地址id")
            @NotNull
    Integer addressId;

}
