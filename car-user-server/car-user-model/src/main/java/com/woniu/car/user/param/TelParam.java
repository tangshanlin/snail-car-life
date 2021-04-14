package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "手机查重校验的参数")
public class TelParam {
@ApiModelProperty(value = "用户电话号码")
        @NotNull(message = "用户电话不能为空")
    String userTel;
}
