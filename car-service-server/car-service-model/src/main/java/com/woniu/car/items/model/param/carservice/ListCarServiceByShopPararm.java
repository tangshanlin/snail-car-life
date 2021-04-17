package com.woniu.car.items.model.param.carservice;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @ClassName ListCarServiceByShopPararm
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/12 10:21
 * @Version 1.0
 */
@Data
public class ListCarServiceByShopPararm {
    @NotNull(message = "shopId不能为空")
    @ApiModelProperty(value = "门店id",example = "1")
    private Integer shopId;
}
