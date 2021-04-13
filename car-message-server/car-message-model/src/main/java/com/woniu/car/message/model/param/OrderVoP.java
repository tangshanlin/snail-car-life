package com.woniu.car.message.model.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderVoP implements Serializable {

    /**
     * 订单编号
     */
    private String orderNo;


    /**
     * 订单状态码
     */
    private String orderStatus;

}
