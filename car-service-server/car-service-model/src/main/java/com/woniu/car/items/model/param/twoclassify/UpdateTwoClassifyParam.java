package com.woniu.car.items.model.param.twoclassify;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateTwoClassifyParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/10 15:34
 * @Version 1.0
 */
@Data
public class UpdateTwoClassifyParam {
    @NotNull(message = "twoClassifyId不能为空")
    @ApiModelProperty(value = "要修改的二级分类id",example = "1")
    private Integer twoClassifyId;

    @NotNull(message = "twoClassifyName不能为空")
    @ApiModelProperty(value = "二级分类名称",example = "洗车")
    private String twoClassifyName;
}
