package com.woniu.car.shop.model.paramVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class FindShopByClassParamVo {

    @ApiModelProperty(value = "所属品牌，可为空")
    private String shopBrand;//所属品牌
}
