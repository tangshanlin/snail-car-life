package com.woniu.car.product.model.library;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Copyright (C), 2021, 温天宇
 *
 * @author WTY
 * Date: 2021/4/13 14:17
 * FileName: CarShopIndex
 * indexName:ES中的索引
 */
@Data
@Document(indexName = "product_index")

public class CarProductIndex implements Serializable {

    /**
     * @Id表示id属性
     * @Field:定义属性字段
     * analyzer:是否加入中文分词 ik_smart粗分 ik_max_word细分
     * type指定属性类型 text 中文分词  integer整形  keyword 直接作为关键词
     *
     */

    /**
     * 商品id
     */
    @Id
    private Integer productId;

    /**
     * 分类id
     */
    @Field(name = "cateId",type = FieldType.Integer)
    private Integer cateId;

    /**
     *
     */
    @Field(name = "productImage")
    private String productImage;

    /**
     * 商品名字
     */
    @Field(name = "productName",analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.Text)
    private String productName;


    /**
     * 商品详情
     */
    @Field(name = "productDetail",analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.Text)
    private String productDetail;


    /**
     * 商品价格
     */
    @Field(name = "productPrice")
    private BigDecimal productPrice;

    /**
     * 秒杀价格
     */
    @Field(name = "productSeckill")
    private BigDecimal productSeckill;

    /**
     * 商品销量
     */
    @Field(name = "productSales")
    private Integer productSales;

    /**
     * 商品库存
     */
    @Field(name = "productStock",type = FieldType.Integer)
    private Integer productStock;


    /**
     * 商品访问量
     */
    @Field(name = "productVisitor",type = FieldType.Integer)
    private Integer productVisitor;

    /**
     * 商品评分
     */
    @Field(name = "productScore")
    private Integer productScore;

    /**
     * 商品品牌
     */
    @Field(name = "productBrand",analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.Text)
    private String productBrand;

    /**
     * 商品是否热销
     */
    @Field(name = "productIshot",type = FieldType.Integer)
    @TableField("product_isHot")
    private Integer productIshot;

    /**
     * 是否新品
     */
    @Field(name = "productIsnew",type = FieldType.Integer)
    @TableField("product_isNew")
    private Integer productIsnew;

    /**
     * 是否包邮
     */
    @Field(name = "productIsfreeshipping",type = FieldType.Integer)
    @TableField("product_isFreeShipping")
    private Integer productIsfreeshipping;

    /**
     * 收藏人数
     */
    @Field(name = "productCollectnum",type = FieldType.Integer)
    @TableField("product_collectNum")
    private Integer productCollectnum;



}
