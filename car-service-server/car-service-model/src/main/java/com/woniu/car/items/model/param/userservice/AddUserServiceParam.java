package com.woniu.car.items.model.param.userservice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName AddUserServiceParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/12 17:04
 * @Version 1.0
 */
@Data
public class AddUserServiceParam {
    @NotNull(message = "userId不能为空")
    @ApiModelProperty(value = "关联用户id")
    private Integer userId;

    @NotNull(message = "carServiceId不能为空")
    @ApiModelProperty(value = "关联服务id")
    private Integer carServiceId;

    @NotNull(message = "userServiceSubscribe不能为空")
    @ApiModelProperty(value = "是否预约")
    private String userServiceSubscribe;

    @NotNull(message = "userServiceSubscribeTime不能为空")
    @ApiModelProperty(value = "预约服务时间")
    private String userServiceSubscribeTime;

}
