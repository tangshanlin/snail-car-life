package com.woniu.car.items.model.param.carservice;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @ClassName GetOneCarServiceParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/12 10:09
 * @Version 1.0
 */
@Data
public class GetOneCarServiceParam {
    @NotNull
    @ApiModelProperty(value = "要查询的具体服务id",example = "1")
    private Integer carServiceId;
}
