package com.woniu.car.message.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "电站标签名字参数信息")
public class SeviceNameCommentParam implements Serializable {

    @ApiModelProperty(value = "门店编号")
    @NotNull(message="门店编号名字不能为空")
    private Integer shopId;

    @ApiModelProperty(value = "服务名字")
    @NotEmpty(message="服务名字不能为空")
    private String serviceName;

}
