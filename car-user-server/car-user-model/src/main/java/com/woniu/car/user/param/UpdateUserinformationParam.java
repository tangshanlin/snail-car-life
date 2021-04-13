package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    @NotNull
    private String userName;

    @ApiModelProperty(value = "用户生日")
    @NotNull
    private Long userBirthday;



    @ApiModelProperty(value = "用户头像")
    @NotNull
    private String userImage;
}
