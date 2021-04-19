package com.woniu.car.user.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "用户注册的参数")
public class RegisterParam {

    @ApiModelProperty(value = "用户帐户")
    @NotEmpty (message = "账户不能为空")
    @Pattern(regexp = "^([A-Z]|[a-z]|[0-9]|[`=[];,./~!@#$%^*()_+}{:?]]){6,8}$",message = "账户为6-8位数字字母字符组合")

    String userAccount;
    @ApiModelProperty(value = "用户手机号")
    @NotEmpty(message = "电话号码不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")


    String userTel;
    @ApiModelProperty(value = "用户密码")
    @NotEmpty(message = "密码不能为空")

    String userPassword;


}
