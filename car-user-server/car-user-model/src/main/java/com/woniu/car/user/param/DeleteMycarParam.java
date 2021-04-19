package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.security.DenyAll;
import javax.validation.constraints.NotNull;

/**
 * @ClassName DeleteMycarParam
 * @Desc TODO 删除我的爱车的接口
 * @Author Administrator
 * @Date 2021/4/12 14:39
 * @Version 1.0
 */
@Data
@ApiModel(value = "删除爱车的参数")
public class DeleteMycarParam {
    @ApiModelProperty(value = "我的车id",example = "1")
    @NotNull(message = "我的爱车id不能为空")
    private Integer mycarId;


}
