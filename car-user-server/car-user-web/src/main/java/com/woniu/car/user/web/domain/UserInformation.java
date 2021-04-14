package com.woniu.car.user.web.domain;

import java.math.BigDecimal;
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
 * 用户信息明细表
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user_information")
@ApiModel(value="UserInformation对象", description="用户信息明细表")
public class UserInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户详情页Id")
      @TableId(value = "user_information_id", type = IdType.AUTO)
    private Integer userInformationId;

    private Integer userId;

    @ApiModelProperty(value = "用户生日")
    private Long userBirthday;

    @ApiModelProperty(value = "用户手机号")
    private String userTel;

    @ApiModelProperty(value = "用户积分")
    private Integer userScore;

    @ApiModelProperty(value = "用户头像")
    private String userImage;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "修改时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "钱包余额")
    private BigDecimal walletMoney;

    @ApiModelProperty(value = "用户名(昵称)")
    private String userName;


}
