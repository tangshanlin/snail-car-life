package com.woniu.car.message.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author Lints
 * @Date 2021/4/7/007 13:43
 * @Description 为评论添加标签
 * @Since version-1.0
 */


@Data
@ApiModel(value = "为评论添加标签参数信息")
public class AddTagsForCommentParam implements Serializable {

    /**
     * 一系列标签编号
     */
    @ApiModelProperty(value = "遍历出来的标签编号")
    @NotNull(message = "标签选择不能为空")
    private Integer[] tagId;

    /**
     * 评论编码
     */
    @NotNull(message = "commentCode不能为空")
    @ApiModelProperty(value = "评论编码")
    private String commentCode;


}
