package com.woniu.car.message.model.param;

import lombok.Data;

import java.io.Serializable;


/**
 * <p>
 *  商品标签查询条件类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Data
public class ProductTagNameLookCommentParam implements Serializable {

    /**
     * 标签名字
     */
    private String tagName;
    /**
     * 商品编号
     */
    private Integer productCode;

}
