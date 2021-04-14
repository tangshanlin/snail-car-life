package com.woniu.car.user.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 车品牌表
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-14
 */
@Data


public class CarBrandDto implements Serializable {



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
