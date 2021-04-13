package com.woniu.car.product.model.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

@Data
public class ProductCateOneDto {
    /**
     * 分类id
     */
    private Integer cateId;
    /**
     * 分类名称
     */
    private String cateName;
    /**
     * 每一个分类
     */
    private List<ProductCateOneDto> childcateList;

    /**
     * 分类图片
     */
    private String cateImage;
    /**
     * JSon图片
     */
    private JSONObject cateImages;
    /**
     * 父id
     */
    private Integer parentId;
    /**
     * 分类等级
     */
    private Integer level;

}
