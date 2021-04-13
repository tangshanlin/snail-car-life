package com.woniu.car.message.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "商品编号参数信息")
public class ProductIdParam implements Serializable {


    @ApiModelProperty(value = "商品编号")
    @NotNull(message="商品编号不能为空")
    private Integer productId;

}
