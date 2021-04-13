package com.woniu.car.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginTelParam {
    @ApiModelProperty(value = "用户电话号码")
            @Size(min = 11,max = 11)
    String userTel;
    @ApiModelProperty(value = "验证码")
    @NotNull
    String code;
}