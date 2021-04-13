package com.woniu.car.commons.core.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniu.car.commons.core.code.ResultEnum;

import java.util.List;

/**
 * @Author Lints
 * @Date 2021/4/6/006 12:06
 * @Description 响应前端类格式
 * @Since version-1.0
 */
public class ResultEntity<T> {

    /**
     *  响应的信息内容
     */
    private String message;
    /**
     * 响应的状态
     */
    private Boolean flag;
    /**
     * 响应的代码编号
     */
    private Integer code;
    /**
     *响应的数据
     */
    private T data;

    /**
     * @Author Lints
     * @Date 2021/4/6/006 12:00
     * @Description 下面的4个方法都是针对不同类型的成功返回
     * @Param []
     * @Return com.woniu.car.commons.core.dto.ResultEntity<?>
     * @Since version-1.0
     */
    public  static ResultEntity<?> buildSuccessEntity(){
        return new ResultEntity().setCode(ResultEnum.RES_SUCCESS.getCode())
                .setFlag(ResultEnum.RES_SUCCESS.getFlag());
    }

    public static <S> ResultEntity<S> buildSuccessEntity(Class<S> type){
        return new ResultEntity<S>().setCode(ResultEnum.RES_SUCCESS.getCode())
                .setFlag(ResultEnum.RES_SUCCESS.getFlag());
    }

    public static <S> ResultEntity<List<S>> buildListSuccessEntity(Class<S> type){
        return new ResultEntity<List<S>>().setCode(ResultEnum.RES_SUCCESS.getCode())
                .setFlag(ResultEnum.RES_SUCCESS.getFlag());
    }

    public static <S> ResultEntity<IPage<S>> buildPageListSuccessEntity(Class<S> type){
        return new ResultEntity<IPage<S>>().setCode(ResultEnum.RES_SUCCESS.getCode())
                .setFlag(ResultEnum.RES_SUCCESS.getFlag());
    }

    /**
     * @Author Lints
     * @Date 2021/4/6/006 12:00
     * @Description 下面的4个方法都是针对不同类型的失败返回
     * @Param []
     * @Return com.woniu.car.commons.core.dto.ResultEntity<?>
     * @Since version-1.0
     */
    public  static ResultEntity<?> buildFailEntity(){
        return new ResultEntity()
                .setFlag(ResultEnum.RES_FAIL.getFlag())
                .setCode(ResultEnum.RES_FAIL.getCode());
    }

    public static <S> ResultEntity<S> buildFailEntity(Class<S> type){
        return new ResultEntity<S>()
                .setFlag(ResultEnum.RES_FAIL.getFlag())
                .setCode(ResultEnum.RES_FAIL.getCode());
    }

    public static <S> ResultEntity<List<S>> buildListFailEntity(Class<S> type){
        return new ResultEntity<List<S>>()
                .setFlag(ResultEnum.RES_FAIL.getFlag())
                .setCode(ResultEnum.RES_FAIL.getCode());
    }

    public static <S> ResultEntity<IPage<S>> buildPageListFailEntity(Class<S> type){
        return new ResultEntity<IPage<S>>()
                .setFlag(ResultEnum.RES_FAIL.getFlag())
                .setCode(ResultEnum.RES_FAIL.getCode());
    }

    /**
     * @Author Lints
     * @Date 2021/4/6/006 12:00
     * @Description 下面的4个方法都是针对不同类型的自定义状态返回
     * @Param []
     * @Return com.woniu.car.commons.core.dto.ResultEntity<?>
     * @Since version-1.0
     */
    public  static ResultEntity<?> buildEntity(){
        return new ResultEntity();
    }

    public static <S> ResultEntity<S> buildEntity(Class<S> type){
        return new ResultEntity<S>();
    }

    public static <S> ResultEntity<List<S>> buildListEntity(Class<S> type){
        return new ResultEntity<List<S>>();
    }

    public static <S> ResultEntity<IPage<S>> buildPageListEntity(Class<S> type){
        return new ResultEntity<IPage<S>>();
    }

    public String getMessage() {
        return message;
    }

    public ResultEntity<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Boolean getFlag() {
        return flag;
    }

    public ResultEntity<T> setFlag(Boolean flag) {
        this.flag = flag;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public ResultEntity<T> setCode(Integer code) {
        this.code = code;
        return this;
    }
    public T getData() {
        return data;
    }

    public ResultEntity<T> setData(T data) {
        this.data = data;
        return this;
    }
    public ResultEntity() {
    }

    public ResultEntity(String message, Integer code) {
        this.message = message;
        this.flag=false;
        this.code = code;
    }
}
