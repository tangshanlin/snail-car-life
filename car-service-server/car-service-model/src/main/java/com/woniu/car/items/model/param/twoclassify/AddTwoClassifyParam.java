package com.woniu.car.items.model.param.twoclassify;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @ClassName AddTwoClassifyParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/10 15:28
 * @Version 1.0
 */
@Data
public class AddTwoClassifyParam {
    @NotNull
    @ApiModelProperty(value = "关联一级分类id",example = "1")
    private Integer oneClassifyId;

    @NotNull
    @ApiModelProperty(value = "二级分类名称",example = "打蜡")
    private String twoClassifyName;
}
