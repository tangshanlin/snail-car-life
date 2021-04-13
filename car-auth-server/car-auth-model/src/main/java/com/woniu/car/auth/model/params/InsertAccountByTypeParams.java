package com.woniu.car.auth.model.params;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.validation.constraints.NotNull;

/**
 * Copyright (C), 2021, 温天宇
 *
 * @author WTY
 * Date: 2021/4/7 15:31
 * FileName: insertAccountByTypeParams
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertAccountByTypeParams {

    @ApiModelProperty(value = "角色类型",example = "1")
    @NotNull(message = "参数不能为空")
    private Integer type;


}
