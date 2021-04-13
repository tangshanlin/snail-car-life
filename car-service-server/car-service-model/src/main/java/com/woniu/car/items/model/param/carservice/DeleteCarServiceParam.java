package com.woniu.car.items.model.param.carservice;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @ClassName DeleteCarServiceParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/12 11:34
 * @Version 1.0
 */
@Data
public class DeleteCarServiceParam {
    @NotNull
    @ApiModelProperty(value = "要删除的具体服务id",example = "1")
    private Integer carServiceId;
}
