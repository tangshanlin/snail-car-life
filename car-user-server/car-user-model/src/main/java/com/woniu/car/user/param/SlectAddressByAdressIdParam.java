package com.woniu.car.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName SlectAddressByAdressIdParam
 * @Desc TODO
 * @Author Administrator
 * @Date 2021/4/10 9:18
 * @Version 1.0
 */
@Data
public class SlectAddressByAdressIdParam {
    @ApiModelProperty(value = "地址ID")
    @NotNull
    private Integer addressId;
}