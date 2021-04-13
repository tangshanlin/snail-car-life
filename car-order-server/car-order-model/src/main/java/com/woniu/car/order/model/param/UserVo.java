package com.woniu.car.order.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@ApiModel(value = "填写用户信息id")
public class UserVo {
    @ApiModelProperty(value = "用户id")
    @NotNull(message = "用户id不能为空")
    public Integer userId;
}
