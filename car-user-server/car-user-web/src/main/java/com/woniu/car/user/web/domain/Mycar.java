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
 * 我的爱车
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_mycar")
@ApiModel(value="Mycar对象", description="我的爱车")
public class Mycar implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "我的车id")
      @TableId(value = "mycar_id", type = IdType.AUTO)
    private Integer mycarId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "车型id")
    private Integer cartypeId;

    @ApiModelProperty(value = "车品牌")
    private String mycarBrand;

    @ApiModelProperty(value = "款型")
    private String mycarType;

    @ApiModelProperty(value = "添加时间")
      @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "修改时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "爱车图片")
    private String mycarImage;

    @ApiModelProperty(value = "里程")
    private Integer mycarKm;

    @ApiModelProperty(value = "车牌号")
    private String mycarCode;

    @ApiModelProperty(value = "生产年份")
    private String mycarProductionDate;

    @ApiModelProperty(value = "发动机排量")
    private String mycarEngineCapacity;

    @ApiModelProperty(value = "上路时间")
    private Long mycarOntheroadTime;

    @ApiModelProperty(value = "注册时间(行驶证上的时间)")
    private Long mycarRegisterTime;


}
