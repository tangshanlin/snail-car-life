package com.woniu.car.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName SelectCarseirsParam
 * @Desc TODO
 * @Author Administrator
 * @Date 2021/4/14 14:55
 * @Version 1.0
 */
@Data
public class SelectCarseirsParam {
    @ApiModelProperty(value = "品牌ID",example = "65")
    @NotNull(message = "参数不能为空")
    private Integer CarbrandId;
}
