package com.woniu.car.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccountParam {
    @ApiModelProperty(value = "用户帐户")
    @NotNull(message = "不能为空")
    private String userAccount;
}
