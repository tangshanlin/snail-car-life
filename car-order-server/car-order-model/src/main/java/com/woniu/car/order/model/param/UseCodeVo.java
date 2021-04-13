package com.woniu.car.order.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    private String useCode;
}
