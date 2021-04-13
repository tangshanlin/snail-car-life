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
 * 地址表
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_address")
@ApiModel(value="Address对象", description="地址表")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "地址id")
      @TableId(value = "address_id", type = IdType.AUTO)
    private Integer addressId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "收件人")
    private String addressContactName;

    @ApiModelProperty(value = "收件人电话")
    private String addressContactTel;

    @ApiModelProperty(value = "邮编")
    private String addressZip;

    @ApiModelProperty(value = "省")
    private String addressProvince;

    @ApiModelProperty(value = "市")
    private String addressCity;

    @ApiModelProperty(value = "区")
    private String addressDistrict;

    @ApiModelProperty(value = "街道地址")
    private String addressStreet;

    @ApiModelProperty(value = "详细地址")
    private String addressDetail;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "修改时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "是否为默认地址(0为默认)")
    private Integer isDefaultAddress;


}
