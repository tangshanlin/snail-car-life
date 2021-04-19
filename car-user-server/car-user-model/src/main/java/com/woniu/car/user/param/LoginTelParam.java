package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Empty;
import sun.plugin2.message.Message;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "手机验证码登陆的参数")
public class LoginTelParam {
    @ApiModelProperty(value = "用户电话号码")
    @NotEmpty(message = "电话号码不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    String userTel;
    @ApiModelProperty(value = "验证码")
    @NotEmpty(message = "验证码不能为空")
    String code;
}
