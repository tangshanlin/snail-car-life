package com.woniu.car.items.model.param.carservice;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName ListCarServiceByTwoClassifyParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/12 10:37
 * @Version 1.0
 */
@Data
public class ListCarServiceByTwoClassifyParam {
    @NotNull(message = "twoClassifyId不能为空")
    @ApiModelProperty(value = "二级分类id",example = "1")
    private Integer twoClassifyId;
}
