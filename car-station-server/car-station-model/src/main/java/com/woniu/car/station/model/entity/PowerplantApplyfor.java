package com.woniu.car.station.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * @author Sokyo
 * @since 2021-04-05
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("t_powerplant_applyfor")
@ApiModel(value="PowerplantApplyfor对象", description="")
public class PowerplantApplyfor implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "powerplant_applyfor_id", type = IdType.AUTO)
      private Integer powerplantApplyforId;

      @ApiModelProperty(value = "关联电站对应账号id")
      private Integer userId;

      @ApiModelProperty(value = "电站名称")
      private String powerplanApplyfortName;

      @ApiModelProperty(value = "电站图片")
      private String powerplantApplyforImage;

      @ApiModelProperty(value = "电站介绍")
      private String powerplantApplyforIntroduce;

      @ApiModelProperty(value = "电站地址")
      private String powerplantApplyforAddress;

      @ApiModelProperty(value = "电站热线电话")
      private String powerplantApplyforPhone;

      @ApiModelProperty(value = "电站直流电电桩数量")
      private Integer powerplantApplyforCocurrentNumber;

    private Integer powerplantApplyforAlternatingNumber;

      @ApiModelProperty(value = "电站经纬度")
      private String powerplantCoordinate;

      @ApiModelProperty(value = "电站申请审核状态0未审核 1审核通过 2审核未通过")
      private Integer powerplantApplyforStatus;

      @ApiModelProperty(value = "创建时间")
        @TableField(fill = FieldFill.INSERT)
      private Long gmtCreate;

      @ApiModelProperty(value = "修改时间")
        @TableField(fill = FieldFill.INSERT_UPDATE)
      private Long gmtModified;

      @ApiModelProperty(value = "删除状态 0未删除 1已删除")
      @TableLogic
    private Integer deleted;


}
