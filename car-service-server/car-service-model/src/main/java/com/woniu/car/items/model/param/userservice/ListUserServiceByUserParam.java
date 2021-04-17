package com.woniu.car.items.model.param.userservice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName ListUserServiceByUserParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/12 17:17
 * @Version 1.0
 */
@Data
public class ListUserServiceByUserParam {
    @NotNull(message = "userId不能为空")
    @ApiModelProperty(value = "关联用户id")
    private Integer userId;
}
