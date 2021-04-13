package com.woniu.car.user.param;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterParam {
    @NotNull(message = "账户不能为空")
    @ApiModelProperty(value = "用户帐户")
    String userAccount;
    @NotNull(message = "电话号码不能为空")
            @Size(min=11,max = 11,message = "手机号必须在6-12位之间")
    @ApiModelProperty(value = "用户手机号")
    String userTel;
    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "用户密码")
    String userPassword;


}
