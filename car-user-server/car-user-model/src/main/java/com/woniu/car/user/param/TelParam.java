package com.woniu.car.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TelParam {
@ApiModelProperty(value = "用户电话号码")
        @NotNull
    String userTel;
}
