package com.woniu.car.user.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "用户注册的参数")
public class RegisterParam {

    @ApiModelProperty(value = "用户帐户")
    @NotNull(message = "账户不能为空")
    String userAccount;
    @ApiModelProperty(value = "用户手机号")
    @NotNull(message = "电话号码不能为空")
            @Size(min=11,max = 11,message = "手机号必须在6-12位之间")

    String userTel;
    @ApiModelProperty(value = "用户密码")
    @NotNull(message = "密码不能为空")

    String userPassword;


}
