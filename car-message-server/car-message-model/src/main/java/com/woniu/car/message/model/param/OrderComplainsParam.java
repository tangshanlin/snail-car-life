package com.woniu.car.message.model.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderComplainsParam implements Serializable {

    /**
     * 投诉订单编号
     */
    private String complaintOrderCode;

    /**
     * 投诉人编号
     */
    private Integer complainUserId;

    /**
     * 投诉人姓名
     */
    private String complainUsername;

    /**
     * 投诉类型（1.服务质量，2 服务人员，3自然灾害，4.服务设备(下拉框选择，传入值)
     */
    private String complainType;

    /**
     * 投诉具体内容
     */
    private String complainResult;
    /**
     * 投诉id,后台产生，前端不用传递
     */
    private Integer complainId;


}
