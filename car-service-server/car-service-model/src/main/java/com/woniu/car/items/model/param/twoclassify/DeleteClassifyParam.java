package com.woniu.car.items.model.param.twoclassify;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @ClassName DeleteClassifyParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/10 15:46
 * @Version 1.0
 */
@Data
public class DeleteClassifyParam {
    @NotNull
    @ApiModelProperty(value = "要删除的二级分类id",example = "1")
    private Integer twoClassifyId;
}
