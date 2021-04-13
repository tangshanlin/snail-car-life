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
 * 驾驶证
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_driver_license")
@ApiModel(value="DriverLicense对象", description="驾驶证")
public class DriverLicense implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "驾驶证id")
      @TableId(value = "driverlicense_id", type = IdType.AUTO)
    private Integer driverlicenseId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "证件真实名")
    private String userRelalName;

    @ApiModelProperty(value = "证件号码")
    private Integer driverlicensePassportNo;

    @ApiModelProperty(value = "性别")
    private String driverlicenseUserGender;

    @ApiModelProperty(value = "国籍")
    private String driverlicenseNation;

    @ApiModelProperty(value = "住址")
    private String driverlicenseAddress;

    @ApiModelProperty(value = "初次领证日期")
    private Long driverlicenseCreate;

    @ApiModelProperty(value = "准驾车型")
    private String driverlicenseCartype;

    @ApiModelProperty(value = "有效期开始时间")
    private Long driverlicenseStarttime;

    @ApiModelProperty(value = "有效结束日期")
    private Long driverlicenseEndtime;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "修改时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;


}
