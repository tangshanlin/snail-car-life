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
 * 车群表
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_car_group")
@ApiModel(value="CarGroup对象", description="车群表")
public class CarGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "车群id1")
      @TableId(value = "cargroup_id", type = IdType.AUTO)
    private Integer cargroupId;

    @ApiModelProperty(value = "车品牌id")
    private Integer carbrandId;

    @ApiModelProperty(value = "车群名")
    private String cargroupName;

    @ApiModelProperty(value = "车群首字母")
    private String cargroupFirstletter;

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
