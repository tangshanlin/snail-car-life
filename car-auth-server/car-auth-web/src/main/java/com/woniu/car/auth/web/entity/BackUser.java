package com.woniu.car.auth.web.entity;

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
 * 
 * </p>
 *
 * @author WTY
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_back_user")
@ApiModel(value="BackUser对象", description="")
public class BackUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "后台用户id")
    @TableId(value = "back_user_id", type = IdType.AUTO)
    private Integer backUserId;

    @ApiModelProperty(value = "后台用户账号")
//    @TableId(value = "back_username")
    private String backUsername;

    @ApiModelProperty(value = "后台用户密码")
    private String backUserPassword;

    @ApiModelProperty(value = "盐")
    private String salt;

    @ApiModelProperty(value = "账号状态")
    private String status;

    @ApiModelProperty(value = "上次登录时间")
    private long lastTime;

    @ApiModelProperty(value = "后台用户邮箱")
    private String backUserEmail;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private String deleted;


    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private long gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private long gmtModified;


}
