package com.woniu.car.commons.core.code;


import lombok.Data;

@Data
public class ConstDate {
    /**
     * 解决返回json字符串中文乱码问题
     */
    public static final String CONTENT_TYPE = "application/json;charset=utf-8";
    /**
     * 前端用户请求头 - token
     */
    public static final String REQUEST_HEADER_TOKEN = "auth_token";


}
