package com.woniu.car.user.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName DeleteUserParam
 * @Desc TODO 删除用户接口
 * @Author Administrator
 * @Date 2021/4/19 17:02
 * @Version 1.0
 */
@Data
public class DeleteUserParam {
    @NotEmpty(message = "密码不能为空")
    private String userPassword;

}
