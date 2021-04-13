package com.woniu.car.message.model.param;

import lombok.Data;

import java.io.Serializable;

@Data

public class SeviceNameCommentParam implements Serializable {
    private Integer shopId;
    private String serviceName;

}
