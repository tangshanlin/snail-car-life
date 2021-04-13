package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "账户校验参数")
public class AccountParam {
    @ApiModelProperty(value = "用户帐户")
    @NotNull(message = "不能为空")
    private String userAccount;
}
