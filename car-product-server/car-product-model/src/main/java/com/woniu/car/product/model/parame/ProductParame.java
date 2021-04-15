package com.woniu.car.product.model.parame;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ProductParame {


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
