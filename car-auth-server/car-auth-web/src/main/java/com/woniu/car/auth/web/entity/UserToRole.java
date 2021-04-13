package com.woniu.car.auth.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2021-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user_to_role")
@ApiModel(value="UserToRole对象", description="")
public class UserToRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户关联角色中间表id")
    @TableId(value = "user_to_role_id", type = IdType.AUTO)
    private Integer userToRoleId;

    @ApiModelProperty(value = "后台用户id")
    private Integer backUserId;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private String deleted;


    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;


}
