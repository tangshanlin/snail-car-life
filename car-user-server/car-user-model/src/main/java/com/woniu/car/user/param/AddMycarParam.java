package com.woniu.car.user.param;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName AddMycarParam
 * @Desc TODO 新增我的爱车的输入参数
 * @Author Administrator
 * @Date 2021/4/12 11:01
 * @Version 1.0
 */
@Data
public class AddMycarParam {
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "车型id")
    private Integer cartypeId;

    @ApiModelProperty(value = "车品牌")
    private String mycarBrand;

    @ApiModelProperty(value = "款型")
    private String mycarType;


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
