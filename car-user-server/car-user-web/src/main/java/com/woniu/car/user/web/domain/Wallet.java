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
 * 钱包表
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_wallet")
@ApiModel(value="Wallet对象", description="钱包表")
public class Wallet implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "钱包id")
      @TableId(value = "wallet_id", type = IdType.AUTO)
    private Integer walletId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "余额")
    private BigDecimal walletMoney;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "修改时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "支付密码")
    private String walletPassword;


}
