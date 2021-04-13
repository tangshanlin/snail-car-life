package com.woniu.car.items.model.param.twoclassify;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @ClassName ListTwoClassifyParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/10 15:23
 * @Version 1.0
 */
@Data
public class ListTwoClassifyParam {

    @NotNull
    @ApiModelProperty(value = "关联一级分类id",example = "1")
    private Integer oneClassifyId;
}
