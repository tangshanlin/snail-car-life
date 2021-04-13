package com.woniu.car.items.model.entity;

import java.math.BigDecimal;
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
    @TableName("t_car_service")
@ApiModel(value="CarService对象", description="")
public class CarService implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "car_service_id", type = IdType.AUTO)
      private Integer carServiceId;

      @ApiModelProperty(value = "关联二级分类id")
      private Integer twoClassifyId;

      @ApiModelProperty(value = "关联门店id")
      private Integer shopId;

      @ApiModelProperty(value = "服务名称")
      private String carServiceName;

      @ApiModelProperty(value = "服务价格")
      private BigDecimal carServicePrice;

      @ApiModelProperty(value = "服务图片")
      private String carServiceImage;

      @ApiModelProperty(value = "服务介绍")
      private String carServiceIntroduce;

      @ApiModelProperty(value = "服务详情")
      private String carServiceInfo;

      @ApiModelProperty(value = "使用车型")
      private String carServiceType;

      @ApiModelProperty(value = "服务状态 0待上架 1已上架 2已下架")
      private Integer carServiceStatus;

      @ApiModelProperty(value = "已售数量")
      private Integer carServiceSold;

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
