package com.woniu.car.shop.web.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 门店服务收益表
 * </p>
 *
 * @author tsl
 * @since 2021-04-05
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("t_shop_service_earnings")
@ApiModel(value="ShopServiceEarnings对象", description="")
public class ShopServiceEarnings implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "门店服务类别收益id")
        @TableId(value = "shop_service_earnings_id", type = IdType.AUTO)
      private Integer shopServiceEarningsId;

      @ApiModelProperty(value = "关联门店id")
      private Integer shopId;

      @ApiModelProperty(value = "服务名称")
      private String carServiceName;

      @ApiModelProperty(value = "该服务门店收益总金额")
      private BigDecimal shopServiceEarningsMoney;

      @ApiModelProperty(value = "该服务平台抽成总金额")
      private BigDecimal shopServicePlatformMoney;

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
