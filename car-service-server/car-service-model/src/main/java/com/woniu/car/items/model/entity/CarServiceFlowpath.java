package com.woniu.car.items.model.entity;

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
    @TableName("t_car_service_flowpath")
@ApiModel(value="CarServiceFlowpath对象", description="")
public class CarServiceFlowpath implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "car_service_flowpath_id", type = IdType.AUTO)
      private Integer carServiceFlowpathId;

      @ApiModelProperty(value = "关联具体服务id")
      private Integer carServiceId;

      @ApiModelProperty(value = "服务名称")
      private String carServiceName;

      @ApiModelProperty(value = "服务流程一")
      private String flowpathOne;

      @ApiModelProperty(value = "服务流程二")
      private String flowpathTwo;

      @ApiModelProperty(value = "服务流程三")
      private String flowpathThree;

      @ApiModelProperty(value = "服务流程四")
      private String flowpathFour;

      @ApiModelProperty(value = "服务流程五(备用字段)")
      private String flowpathFive;

      @ApiModelProperty(value = "服务流程六(备用字段)")
      private String flowpathSix;

      @ApiModelProperty(value = "服务流程七(备用字段)")
      private String flowpathSeven;

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
