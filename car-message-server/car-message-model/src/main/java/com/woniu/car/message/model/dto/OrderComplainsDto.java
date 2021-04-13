package com.woniu.car.message.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderComplainsDto  implements Serializable {

    /**
     * 投诉类型（1.服务质量，2 服务人员，3自然灾害，4.服务设备(下拉框选择，传入值)
     */
    private String complainType;

    /**
     * 投诉具体内容
     */
    private String complainResult;

    /**
     * 投诉时间毫秒数
     */
    private Long complainTime;

    /**
     * 投诉具体时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date complainTimes;






}
