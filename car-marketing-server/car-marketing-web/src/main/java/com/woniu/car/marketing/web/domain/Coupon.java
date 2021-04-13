package com.woniu.car.marketing.web.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 优惠券类别表
 * </p>
 *
 * @author tsl
 * @since 2021-04-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("t_coupon")
@ApiModel(value="Coupon对象", description="")
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "优惠券主键id")
        @TableId(value = "coupon_id", type = IdType.AUTO)
      private Integer couponId;

      @ApiModelProperty(value = "优惠券名称")
      private String couponName;

      @ApiModelProperty(value = "优惠券说明")
      private String couponInfo;

      @ApiModelProperty(value = "优惠券面额(元)")
      private BigDecimal couponMoney;

      @ApiModelProperty(value = "使用门槛（满多少可用）")
      private BigDecimal couponCondition;

      @ApiModelProperty(value = "发行来源(0平台-其他对应门店id)")
      private Integer couponGoods;

      @ApiModelProperty(value = "总发行量")
      private Integer couponNumber;

      @ApiModelProperty(value = "生效时间")
      private Long couponBeginTime;

      @ApiModelProperty(value = "失效时间")
      private Long couponEndTime;

      @ApiModelProperty(value = "已领取数量")
      private Integer couponGetNumber;

      @ApiModelProperty(value = "待领取数量")
      private Integer couponNoGetNumber;

      @ApiModelProperty(value = "已使用数量")
      private Integer couponUseNumber;

      @ApiModelProperty(value = "未使用数量")
      private Integer couponNoUseNumber;

      @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)           //新增时自动赋值
      private Long gmtCreate;

      @ApiModelProperty(value = "修改时间")
        @TableField(fill = FieldFill.INSERT_UPDATE)
      private Long gmtModified;

      @ApiModelProperty(value = "逻辑删除")
      @TableLogic
    private Integer deleted;


}
