package com.woniu.car.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserUpdateParam {
    @ApiModelProperty(value = "用户电话")
    @NotNull
    private String userTel;
    @ApiModelProperty(value = "用户密码")
    @NotNull
    private String userPassword;
    @ApiModelProperty(value = "验证码")
    @NotNull
    private String code;
    @ApiModelProperty(value = "用户账户")
    @NotNull
    private String userAccount;

}
