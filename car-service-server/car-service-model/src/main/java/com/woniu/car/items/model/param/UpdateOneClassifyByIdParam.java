package com.woniu.car.items.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
public class UpdateOneClassifyByIdParam {
    @NotNull
    @ApiModelProperty(value = "要修改一级服务分类的id",example = "1")
    private Integer oneClassifyId;

    @NotNull
    @ApiModelProperty(value = "接收修改的信息",example = "洗车服务")
    private String oneClassifyName;

}
