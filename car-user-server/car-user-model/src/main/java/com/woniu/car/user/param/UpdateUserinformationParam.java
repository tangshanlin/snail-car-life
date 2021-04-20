package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Empty;
import sun.plugin2.message.Message;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName UpdateUserinformationParam
 * @Desc TODO 修改用户详情表接口
 * @Author Administrator
 * @Date 2021/4/10 18:38
 * @Version 1.0
 */
@Data
@ApiModel(value = "用户信息更新的参数")
public class UpdateUserinformationParam {
    @ApiModelProperty(value = "用户名(昵称)")
    @NotEmpty(message = "用户昵称不能为空")
    private String userName;

    @ApiModelProperty(value = "用户生日")
    @NotNull(message = "用户生日")
    private Long userBirthday;



}
