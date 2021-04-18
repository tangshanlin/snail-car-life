package com.woniu.car.items.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
public class DeleteOneClassifyByIdParam {
    @NotNull(message = "oneClassifyId不能为空")
    @ApiModelProperty(value = "要删除的一级服务分类id",example = "1")
    private Integer oneClassifyId;

}
