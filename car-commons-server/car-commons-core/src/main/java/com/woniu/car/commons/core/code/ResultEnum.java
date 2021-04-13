package com.woniu.car.commons.core.code;

public enum ResultEnum {

    /**
     * 返回结果为成功套用模板
     */
    RES_SUCCESS(ConstCode.ACCESS_SUCCESS,true,"访问成功"),
    /**
     * 返回结果为失败套用模板
     */
    RES_FAIL(ConstCode.LAST_STAGE,false,"访问失败");

    /**
     *
     */


    /**
     *  规定的返回码
     */

    Integer code;
    /**
     * 规定的返回信息
     */

    String message;
    /**
     * 规定的返回状态
     */
    Boolean flag;

    /**
     * 以下就是属性的getter()方法
     */
    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getFlag() {
        return flag;
    }
    /**
     * 本类的构造方法
     */
    ResultEnum(Integer code, Boolean flag , String message) {
        this.code = code;
        this.message = message;
        this.flag = flag;
    }

}
