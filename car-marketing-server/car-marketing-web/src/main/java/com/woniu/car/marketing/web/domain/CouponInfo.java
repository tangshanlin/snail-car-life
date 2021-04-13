package com.woniu.car.marketing.web.domain;

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
 * 用户领取优惠券表
 * </p>
 *
 * @author tsl
 * @since 2021-04-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("t_coupon_info")
@ApiModel(value="CouponInfo对象", description="")
public class CouponInfo implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "用户优惠券详情id")
        @TableId(value = "coupon_info_id", type = IdType.AUTO)
      private Integer couponInfoId;

      @ApiModelProperty(value = "关联优惠卷表")
      private Integer couponId;

      @ApiModelProperty(value = "关联用户id")
      private Integer couponInfoUserId;

      @ApiModelProperty(value = "关联的用户昵称")
      private String couponInfoUserNickname;

      @ApiModelProperty(value = "领取时间")
      private Long couponInfoGetTime;

      @ApiModelProperty(value = "当前状态(1已使用0未使用)")
      private Integer couponInfoState;

      @ApiModelProperty(value = "使用时间")
      private Long couponInfoUseTime;

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
