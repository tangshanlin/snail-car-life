package com.woniu.car.message.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 用户信息明细表
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserInformation对象", description="用户信息明细表")
public class UserInformation implements Serializable {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "用户手机号")
    private String userTel;

    @ApiModelProperty(value = "用户头像")
    private String userImage;

    @ApiModelProperty(value = "用户名(昵称)")
    private String userName;

}
