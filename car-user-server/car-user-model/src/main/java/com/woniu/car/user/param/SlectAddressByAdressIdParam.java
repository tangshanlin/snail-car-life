package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName SlectAddressByAdressIdParam
 * @Desc TODO
 * @Author Administrator
 * @Date 2021/4/10 9:18
 * @Version 1.0
 */
@Data
@ApiModel(value = "通过地址id查询地址的参数")
public class SlectAddressByAdressIdParam {
    @ApiModelProperty(value = "地址ID",example = "1")
    @NotNull(message = "地址id不能为空")
    private Integer addressId;
}
