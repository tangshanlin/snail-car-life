package com.woniu.car.message.web.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *  商品评论表
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_product_comment")
@ApiModel(value = "ProductComment对象", description = "")
public class ProductComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "comment_pid", type = IdType.AUTO)
    private Integer commentPid;

    @ApiModelProperty(value = "评论编号")
    private String commentPcode;
    @ApiModelProperty(value = "商品评论用户编号")
    private Integer commentUserId;
    @ApiModelProperty(value = "商品评论用户名字")
    private String commentName;
    @ApiModelProperty(value = "评论用户头像")
    private String commentUrl;
    @ApiModelProperty(value = "评分")
    private Integer commentScore;
    @ApiModelProperty(value = "评论内容")
    private String commentWords;
    @ApiModelProperty(value = "评论图片")
    private String commentImage;
    @ApiModelProperty(value = "评论时间")
    private Long commentTime;
    @ApiModelProperty(value = "评论订单编号")
    private String commentOrderCode;
    @ApiModelProperty(value = "评论商品编号")
    private Integer productCode;
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;
    @TableLogic
    private Integer deleted;


}
