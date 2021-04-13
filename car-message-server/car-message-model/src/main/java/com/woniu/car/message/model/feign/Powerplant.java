package com.woniu.car.message.model.feign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@ApiModel(value="Powerplant对象", description="")
public class Powerplant implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "电站id")
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



}
