package com.woniu.car.station.model.entity;

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
    @TableName("t_station")
@ApiModel(value="Station对象", description="")
public class Station implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "电桩id")
        @TableId(value = "station_id", type = IdType.AUTO)
      private Integer stationId;

      @ApiModelProperty(value = "关联电站id")
      private Integer powerplantId;

      @ApiModelProperty(value = "电桩编号")
      private String stationNumeration;

      @ApiModelProperty(value = "电桩品牌")
      private String stationBrand;

      @ApiModelProperty(value = "电桩图片")
      private String stationImage;

      @ApiModelProperty(value = "电桩每度电的价格")
      private BigDecimal stationPrice;

      @ApiModelProperty(value = "电桩的充电类型: 0直流电  1交流电")
      private Integer stationType;

      @ApiModelProperty(value = "电桩状态0空闲 1充电中")
      private Integer stationStatus;

      @ApiModelProperty(value = "充电最小功率")
      private String stationMinimumPower;

      @ApiModelProperty(value = "充电最大功率")
      private String stationMaximumPower;

      @ApiModelProperty(value = "电桩二维码信息")
      private String stationCode;

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
