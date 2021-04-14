package com.woniu.car.product.web.domain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * <p>
 * 先查商品的一级分类，提供给前端一个URL，在查二级分类，每一个二级分类提供一个URL，供前端调用，最后查根据商品id查商
 * </p>
 *
 * @author cx
 * @since 2021-04-06
 * indexName: ES中的索引 mysql中为库
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_product")
@ApiModel(value = "Product对象", description = "先查商品的一级分类，提供给前端一个URL，在查二级分类，每一个二级分类提供一个URL，供前端调用，最后查根据商品id查商")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "商品id")
    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;

    @ApiModelProperty(value = "分类id")
    private Integer cateId;

    @ApiModelProperty(value = "商品名字")
    private String productName;

    @ApiModelProperty(value = "商品图片")
    private String productImage;

    @ApiModelProperty(value = "商品详情")
    private String productDetail;

    @ApiModelProperty(value = "商品状态")
    private Integer productStatus;

    @ApiModelProperty(value = "库存")
    private Integer productStock;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "秒杀价格")
    private BigDecimal productSeckill;

    @ApiModelProperty(value = "销量")
    private Integer productSales;

    @ApiModelProperty(value = "访问量")
    private Integer productVisitor;

    @ApiModelProperty(value = "评分")
    private Integer productScore;

    @ApiModelProperty(value = "品牌")
    private String productBrand;

    @ApiModelProperty(value = "是否热销")
    @TableField("product_isHot")
    private Integer productIshot;

    @ApiModelProperty(value = "是否新品")
    @TableField("product_isNew")
    private Integer productIsnew;

    @ApiModelProperty(value = "是否包邮")
    @TableField("product_isFreeShipping")
    private Integer productIsfreeshipping;

    @ApiModelProperty(value = "收藏人数")
    @TableField("product_collectNum")
    private Integer productCollectnum;


}
