package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "用户更新的参数")
public class UserUpdateParam {
    @ApiModelProperty(value = "用户电话")
    @NotEmpty(message = "电话号码不能为空")
    private String userTel;
    @ApiModelProperty(value = "用户密码")
    @NotEmpty(message = "用户密码不能为空")
    private String userPassword;
    @ApiModelProperty(value = "验证码")
    @NotEmpty(message = "验证码不能为空")
    private String code;
    @ApiModelProperty(value = "用户账户")
    @NotEmpty(message = "用户账户不能为空")
    private String userAccount;

}
