package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "密码登陆的参数")
public class LoginPasswordParam {
    @ApiModelProperty(value = "用户帐户名")
    @NotEmpty(message = "用户帐户不能为空")
    private String userAccount;
    @ApiModelProperty(value = "用户密码")
    @NotEmpty(message = "用户密码不能为空")
    private String userPassword;

}
