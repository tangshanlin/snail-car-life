package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateMyCarParam
 * @Desc TODO 更新我的爱车的参数
 * @Author Administrator
 * @Date 2021/4/12 14:13
 * @Version 1.0
 */
@Data
@ApiModel(value = "爱车信息更新的参数")
public class UpdateMyCarParam {

    @ApiModelProperty(value = "用户id")
    @NotNull
    private Integer userId;

    @ApiModelProperty(value = "里程")
    @NotNull
    private Integer mycarKm;






}
