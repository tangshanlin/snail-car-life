package com.woniu.car.message.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Data
@ApiModel(value = "添加订单投诉参数信息")
public class OrderComplainsParam implements Serializable {

    /**
     * 投诉订单编号
     */
    @ApiModelProperty(value = "投诉订单编号")
    @NotEmpty(message="投诉订单编号不能为空")
    private String complaintOrderCode;

    /**
     * 投诉人编号
     */
    @ApiModelProperty(value = "投诉人编号")
    @Null(message="投诉人编号从token中获取")
    private Integer complainUserId;

    /**
     * 投诉人姓名
     */
    @ApiModelProperty(value = "投诉人姓名")
    @Null(message="投诉人姓名从数据库中获取")
    private String complainUsername;

    /**
     * 投诉类型（1.服务质量，2 服务人员，3自然灾害，4.服务设备(下拉框选择，传入值)
     */
    @ApiModelProperty(value = "投诉类型")
    @NotEmpty(message="投诉类型（1.服务质量，2 服务人员，3自然灾害，4.服务设备(下拉框选择，传入值)")
    private String complainType;

    /**
     * 投诉具体内容
     */
    @ApiModelProperty(value = "投诉具体内容")
    @NotEmpty(message="投诉具体内容不能为空")
    private String complainResult;
    /**
     * 投诉id,后台产生，前端不用传递
     */
    @Null
    private Integer complainId;

}
