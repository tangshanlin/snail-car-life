package com.woniu.car.auth.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@TableName("t_permission")
@ApiModel(value="Permission对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限id")
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Integer permissionId;

    @ApiModelProperty(value = "权限名称")
    private String permissionName;

    @ApiModelProperty(value = "路由跳转路径")
    private String url;

    @ApiModelProperty(value = "菜单等级")
    private String level;

    @ApiModelProperty(value = "菜单父级id")
    private Integer parentId;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private String deleted;


    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(exist = false)
    private List<Permission> children;


}
