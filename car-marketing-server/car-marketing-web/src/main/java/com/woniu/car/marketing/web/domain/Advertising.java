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
 * 对应活动表
 * </p>
 *
 * @author tsl
 * @since 2021-04-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("t_advertising")
@ApiModel(value="Advertising对象", description="")
public class Advertising implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "活动主键id")
        @TableId(value = "advertising_id", type = IdType.AUTO)
      private Integer advertisingId;

      @ApiModelProperty(value = "活动图片")
      private String advertisingImage;

      @ApiModelProperty(value = "发布来源(0平台其他对应门店对应id)")
      private Integer advertisingSourceId;

      @ApiModelProperty(value = "审核状态（0未审核1已审核2拒绝）")
      private Integer advertisingAudit;

      @ApiModelProperty(value = "关联优惠券id")
      private Integer couponId;

      @ApiModelProperty(value = "活动说明")
      private String advertisingExplain;

      @ApiModelProperty(value = "活动开始时间")
      private Long advertisingBeginTime;

      @ApiModelProperty(value = "活动结束时间")
      private Long advertisingEndTime;

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
