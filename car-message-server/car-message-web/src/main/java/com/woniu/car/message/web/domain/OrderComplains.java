package com.woniu.car.message.web.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 订单投诉表
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_order_complains")
@ApiModel(value = "OrderComplains对象", description = "")
public class OrderComplains implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单投诉表编号")
    @TableId(value = "complaint_id", type = IdType.AUTO)
    private Integer complaintId;

    @ApiModelProperty(value = "投诉订单编号")
    private String complaintOrderCode;

    @ApiModelProperty(value = "投诉人编号")
    private Integer complainUserId;

    @ApiModelProperty(value = "投诉人姓名")
    private String complainUsername;

    @ApiModelProperty(value = "投诉类型（1.服务质量，2 服务人员，3自然灾害，4.服务设备")
    private String complainType;

    @ApiModelProperty(value = "投诉具体内容")
    private String complainResult;

    @ApiModelProperty(value = "投诉时间")
    private Long complainTime;

    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;
    @TableLogic
    private Integer deleted;




}
