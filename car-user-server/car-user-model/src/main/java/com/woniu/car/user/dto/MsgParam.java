package com.woniu.car.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class MsgParam {
    String code;
    String phoneNum;
}
