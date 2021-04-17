package com.woniu.car.items.model.param.userservice;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName DeleteUserServiceParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/12 17:35
 * @Version 1.0
 */
@Data
public class DeleteUserServiceParam {
    @NotNull(message = "userServiceId不能为空")
    @ApiModelProperty(value = "要删除的用户服务关联id")
    private Integer userServiceId;
}
