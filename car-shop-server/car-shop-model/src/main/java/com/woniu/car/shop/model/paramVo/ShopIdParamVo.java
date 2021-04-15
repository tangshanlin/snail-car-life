package com.woniu.car.shop.model.paramVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "门店id参数")
public class ShopIdParamVo {

    @ApiModelProperty(value = "门店id",example = "1")
    @Min(value = 1,message = "门店id必须大于等于1")
    @NotNull(message = "门店id不能为空")
    private Integer shopId;//门店id
}
