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
 * 钱包日志
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_walletlog")
@ApiModel(value="Walletlog对象", description="钱包日志")
public class Walletlog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "钱包日志id")
      @TableId(value = "walletlog_id", type = IdType.AUTO)
    private Integer walletlogId;

    @ApiModelProperty(value = "钱包id")
    private Integer walletId;

    @ApiModelProperty(value = "变化数")
    private BigDecimal walletChange;

    @ApiModelProperty(value = "变化前")
    private BigDecimal walletOld;

    @ApiModelProperty(value = "变化后")
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

    @ApiModelProperty(value = "事件")
    private String walletlogEvent;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "类型(1充值2消费3退款4体现)")
    private Integer walletlogType;


}
