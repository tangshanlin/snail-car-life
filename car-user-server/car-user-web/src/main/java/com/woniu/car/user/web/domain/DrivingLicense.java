package com.woniu.car.user.web.domain;

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
 * 行驶证
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_driving_license")
@ApiModel(value="DrivingLicense对象", description="行驶证")
public class DrivingLicense implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "行驶证id")
      @TableId(value = "drivinglicense_id", type = IdType.AUTO)
    private Integer drivinglicenseId;

    private Integer userId;

    @ApiModelProperty(value = "dr行驶证编号")
    private String drivinglicenseCode;

    @ApiModelProperty(value = "住址")
    private String drivinglicenseAddress;

    @ApiModelProperty(value = "发证日期")
    private Long drivinglicenseDate;

    @ApiModelProperty(value = "品牌型号")
    private String drivinglicenseBrand;

    @ApiModelProperty(value = "车辆类型")
    private String drivinglicenseCartype;

    @ApiModelProperty(value = "持证人")
    private String userRealName;

    @ApiModelProperty(value = "使用性质")
    private Integer drivinglicenseType;

    @ApiModelProperty(value = "发动机号码")
    private String engineNumber;

    @ApiModelProperty(value = "车牌号码")
    private String carCode;

    @ApiModelProperty(value = "注册日期")
    private Long registerDate;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "修改时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "删除")
    @TableLogic
    private Integer deleted;


}
