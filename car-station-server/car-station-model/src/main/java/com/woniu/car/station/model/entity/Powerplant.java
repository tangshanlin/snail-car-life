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
    @TableName("t_powerplant")
@ApiModel(value="Powerplant对象", description="")
public class Powerplant implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "电站id")
        @TableId(value = "powerplant_id", type = IdType.AUTO)
      private Integer powerplantId;

      @ApiModelProperty(value = "关联电站对应账号id")
      private Integer userId;

      @ApiModelProperty(value = "电站名称")
      private String powerplanName;

      @ApiModelProperty(value = "电站图片")
      private String powerplantImage;

      @ApiModelProperty(value = "电站介绍")
      private String powerplantIntroduce;

      @ApiModelProperty(value = "电站地址")
      private String powerplantAddress;

      @ApiModelProperty(value = "电站热线电话")
      private String powerplantPhone;

      @ApiModelProperty(value = "电站直流电电桩数量")
      private Integer powerplantCocurrentNumber;

  @ApiModelProperty(value = "电站交流电电桩数量")
    private Integer powerplantAlternatingNumber;

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
