package com.woniu.car.message.model.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Lints
 * @Date 2021/4/7/007 13:43
 * @Description 为评论添加标签
 * @Since version-1.0
 */


@Data
public class AddTagsForCommentParam implements Serializable {

    /**
     * 一系列标签编号
     */
    private Integer[] tagId;

    /**
     * 评论编码
     */
    private String commentCode;

}
