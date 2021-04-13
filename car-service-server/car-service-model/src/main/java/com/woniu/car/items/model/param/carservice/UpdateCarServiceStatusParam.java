package com.woniu.car.items.model.param.carservice;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateCarServiceStatusParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/12 11:17
 * @Version 1.0
 */
@Data
public class UpdateCarServiceStatusParam {
    @NotNull
    @ApiModelProperty(value = "要修改服务状态的服务id",example = "1")
    private Integer carServiceId;

    @NotNull
    @ApiModelProperty(value = "服务状态 0待上架 1已上架 2已下架",example = "2")
    private Integer carServiceStatus;
}
