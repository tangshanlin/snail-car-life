package com.woniu.car.user.web.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
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
 * 车型信息表
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_car_information")
@ApiModel(value="CarInformation对象", description="车型信息表")
public class CarInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
      @TableId(value = "carinformation_id", type = IdType.AUTO)
    private Integer carinformationId;

    @ApiModelProperty(value = "品牌id")
    private Integer carbrandId;

    @ApiModelProperty(value = "车群id")
    private Integer cargroupId;

    @ApiModelProperty(value = "系列id")
    private Integer carseriesId;

    @ApiModelProperty(value = "全名")
    private String carinformationFullName;

    @ApiModelProperty(value = "名字")
    private String carinformationName;

    @ApiModelProperty(value = "品牌名字")
    private String carbrandName;

    @ApiModelProperty(value = "车群名")
    private String cargroupName;

    @ApiModelProperty(value = "车系名")
    private String carseriesName;

    @ApiModelProperty(value = "状态")
    private Integer carinformationState;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "修改时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "最高价格")
    private BigDecimal maxprice;

    @ApiModelProperty(value = "最小价格")
    private BigDecimal miniprice;

    @ApiModelProperty(value = "上市年份")
    private Integer year;

    @ApiModelProperty(value = "删除")
    @TableLogic
    private Integer deleted;


}
