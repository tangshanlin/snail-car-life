package com.woniu.car.user.web.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
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
 * 车品牌表
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_car_brand")
@ApiModel(value="CarBrand对象", description="车品牌表")
public class CarBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer carbrandId;

    @ApiModelProperty(value = "品牌名字")
    private String carbrandName;

    @ApiModelProperty(value = "图片")
    private String carbrandImage;

    @ApiModelProperty(value = "首字母")
    private String carbrandFirstletter;

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
