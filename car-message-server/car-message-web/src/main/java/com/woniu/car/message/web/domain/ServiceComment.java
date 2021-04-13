package com.woniu.car.message.web.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *  服务评论表
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_service_comment")
@ApiModel(value = "ServiceComment对象", description = "")
public class ServiceComment implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "comment_sid", type = IdType.AUTO)
    private Integer commentSid;

    @ApiModelProperty(value = "服务评论编号")
    private String commentSecode;
    @ApiModelProperty(value = "服务评论用户id")
    private Integer commentUserId;
    @ApiModelProperty(value = "服务评论用户名字")
    private String commentName;
    @ApiModelProperty(value = "服务评论用户头像")
    private String commentUrl;
    @ApiModelProperty(value = "服务评分")
    private Integer commentScore;
    @ApiModelProperty(value = "服务评论内容")
    private String commentWords;
    @ApiModelProperty(value = "服务评论图片")
    private String commentImage;
    @ApiModelProperty(value = "服务评论时间")
    private Long commentTime;
    @ApiModelProperty(value = "服务评论订单号")
    private String commentOrderCode;
    @ApiModelProperty(value = "服务评论门店名字")
    private String commentShopname;
    @ApiModelProperty(value = "服务评论门店编号")
    private Integer commentShopCode;
    @ApiModelProperty(value = "服务评论服务编号")
    private Integer commentServiceCode;
    @ApiModelProperty(value = "服务评论服务名字")
    private String serviceName;
    @TableLogic
    private Integer deleted;
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;
}
