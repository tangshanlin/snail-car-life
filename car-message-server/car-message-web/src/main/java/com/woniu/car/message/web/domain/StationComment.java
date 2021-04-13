package com.woniu.car.message.web.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 车站评价表
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_station_comment")
@ApiModel(value = "StationComment对象", description = "")
public class StationComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "comment_staid", type = IdType.AUTO)
    private Integer commentStaid;
    @ApiModelProperty(value = "评论编号")
    private String commentStcode;
    @ApiModelProperty(value = "评论用户Id")
    private Integer commentUserId;
    @ApiModelProperty(value = "评论用户名字")
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
    @ApiModelProperty(value = "电站评论编号")
    private Integer commentPowerCode;
    @ApiModelProperty(value = "电站评论名字")
    private Integer commentPowerName;

    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @TableLogic
    private Integer deleted;
}
