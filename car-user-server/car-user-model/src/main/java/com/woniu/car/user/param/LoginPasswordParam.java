package com.woniu.car.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginPasswordParam {
    @ApiModelProperty(value = "用户帐户名")
    @NotNull
    private String userAccount;
    @ApiModelProperty(value = "用户密码")
    @NotNull
    private String userPassword;

}
