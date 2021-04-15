package com.woniu.car.product.model.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类DTO，反馈给前端
 */
@Data
public class ProductCateDto {
    /**
     * 分类名称
     */
    private String cateName;
    /**
     * 分类id
     */
    private Integer cateId;
    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 商品图片
     */
    private String cateImage;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 是否活跃
     */
    private Integer isActive;

}
