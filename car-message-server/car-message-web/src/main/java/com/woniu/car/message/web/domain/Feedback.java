package com.woniu.car.message.web.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 投诉反馈表
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_feedback")
@ApiModel(value = "Feedback对象", description = "")
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "投诉反馈表编号")
    @TableId(value = "feedback_id", type = IdType.AUTO)
    private Integer feedbackId;

    @ApiModelProperty(value = "投诉编号")
    private Integer complainId;

    @ApiModelProperty(value = "处理人编号")
    private Integer feedbackDealId;

    @ApiModelProperty(value = "处理人姓名")
    private String feedbackName;

    @ApiModelProperty(value = "处理人电话")
    private String feedbackTel;

    @ApiModelProperty(value = "处理结果")
    private String feedbackResult;

    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;
    @TableLogic
    private Integer deleted;

}
