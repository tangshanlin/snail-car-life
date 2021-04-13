package com.woniu.car.auth.model.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Copyright (C), 2021, 温天宇
 *
 * @author WTY
 * Date: 2021/4/7 18:51
 * FileName: BackUserLoginParams
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackUserLoginParams {

    @ApiModelProperty(value = "后台用户密码")
    @NotNull(message = "密码不能为空")
    @Size(max = 12,min = 6,message = "密码长度6到10位")
    private String backUserPassword;

    @ApiModelProperty(value = "后台用户账号")
    @NotNull(message = "用户名不能为空")
    private String backUsername;

}
