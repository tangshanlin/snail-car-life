package com.woniu.car.items.model.param.userservice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ListUserServiceByUserParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/12 17:17
 * @Version 1.0
 */
@Data
public class ListUserServiceByUserParam {
    @ApiModelProperty(value = "关联用户id")
    private Integer userId;
}
