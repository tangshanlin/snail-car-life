package com.woniu.car.items.model.param.userservice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName AddUserServiceParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/12 17:04
 * @Version 1.0
 */
@Data
public class AddUserServiceParam {
    @ApiModelProperty(value = "关联用户id")
    private Integer userId;

    @ApiModelProperty(value = "关联服务id")
    private Integer carServiceId;

    @ApiModelProperty(value = "是否预约")
    private String userServiceSubscribe;

    @ApiModelProperty(value = "预约服务时间")
    private String userServiceSubscribeTime;

}
