package com.woniu.car.message.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "添加电站编号信息")
public class PowerIdParam  implements Serializable {

    @NotNull(message = "电站id不能为空")
    @ApiModelProperty(value = "电站id")
    private Integer powerId;


}
