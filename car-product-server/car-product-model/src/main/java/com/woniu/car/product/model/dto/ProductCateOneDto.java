package com.woniu.car.product.model.dto;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductCateOneDto {
    @ApiModelProperty(value = "分类id")
    @TableId(value = "cate_id", type = IdType.AUTO)
    private Integer cateId;

    @ApiModelProperty(value = "分类名称")
    private String cateName;

    @ApiModelProperty(value = "分类状态")
    private Integer cateStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "分类图片")
    private String cateImage;

    @ApiModelProperty(value = "父id")
    private Integer parentId;


    private Integer level;

    @ApiModelProperty(value = "是否活跃")
    private String isActive;

    @TableField(exist = false)
    private List<ProductCateOneDto> childcateList;
}
