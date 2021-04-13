package com.woniu.car.items.model.param.userservice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UpdateUserCarServiceStatusParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/12 19:02
 * @Version 1.0
 */
@Data
public class UpdateUserCarServiceStatusParam {
    @ApiModelProperty(value = "要修改的用户服务id")
    private Integer userServiceId;

    @ApiModelProperty(value = "服务状态 0未开始服务  1已开始服务  2服务完成")
    private Integer userServiceStatus;
}
