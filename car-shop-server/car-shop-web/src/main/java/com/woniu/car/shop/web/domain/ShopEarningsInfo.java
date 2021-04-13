package com.woniu.car.shop.web.domain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 门店服务项目具体收益表
 * </p>
 *
 * @author tsl
 * @since 2021-04-05
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("t_shop_earnings_info")
@ApiModel(value="ShopEarningsInfo对象", description="")
public class ShopEarningsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "门店服务收益id")
      @TableId(value = "shop_earnings_info_id", type = IdType.AUTO)
    private Integer shopEarningsInfoId;

    @ApiModelProperty(value = "关联门店服务收益表")
    private Integer shopServiceEarningsId;

    @ApiModelProperty(value = "用户账号")
    private String userAccount;

    @ApiModelProperty(value = "付款时间")
    private Long payTime;

    @ApiModelProperty(value = "该次服务门店收益")
    private BigDecimal carServicePrice;

    @ApiModelProperty(value = "该次服务平台抽成金额")
    private BigDecimal shopServiceInfoPlatformMoney;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)           //新增时自动赋值
    private Long gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)    //新增或修改时自动赋值
    private Long gmtModified;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;


}
