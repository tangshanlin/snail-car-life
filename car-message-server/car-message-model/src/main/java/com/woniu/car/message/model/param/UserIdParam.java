package com.woniu.car.message.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "用户编号参数信息")
public class UserIdParam implements Serializable {

    @ApiModelProperty(value = "商品编号，token中获取",example = "1")
    private Integer userId;

}
