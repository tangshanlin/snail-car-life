package com.woniu.car.user.web.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
      @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "用户账户(6位字母+数字)")
    private String userAccount;

    @ApiModelProperty(value = "用户手机")
    private String userTel;

    @ApiModelProperty(value = "绑定时间")
      @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "更新时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "密码")
    private String userPassword;

    @ApiModelProperty(value = "盐")
    private String userSalt;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "状态")
    private Integer userStatus;

    @ApiModelProperty(value = "上次登陆时间")
    private Long userLastLoginTime;


}
