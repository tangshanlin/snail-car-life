package com.woniu.car.message.model.param;

import lombok.Data;

import java.io.Serializable;


/**
 * <p>
 *  电站标签查询条件类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Data
public class StationTagNameLookCommentParam implements Serializable {

    /**
     * 标签名字
     */
    private String tagName;
    /**
     * 电站编号
     */
    private Integer commentPowerCode;

}
