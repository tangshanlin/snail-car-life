package com.woniu.car.commons.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CarException extends RuntimeException {

    /**
     * CarException版本号
     */

    private static final long serialVersionUID=1L;
    /**
     * 自定义异常号
     */
    private Integer code;

    /**
     * @Author Lints
     * @Date 2021/4/6/006 11:42
     * @Description 自定义异常类
     * @Param [message, code]
     * @Return
     * @Since version-1.0
     */

    public CarException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
