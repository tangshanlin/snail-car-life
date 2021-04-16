package com.woniu.car.order.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName UseCodeVo
 * @Desc TODO
 * @Author 21174
 * @Date 2021/4/11 0:40
 * @Version 1.0
 */

@Data
public class UseCodeVo {

    @ApiModelProperty(value = "使用码")
    @NotEmpty(message = "使用码不能为空")
    private String useCode;
}
