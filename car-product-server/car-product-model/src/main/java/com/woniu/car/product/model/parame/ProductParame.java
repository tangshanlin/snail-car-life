package com.woniu.car.product.model.parame;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ProductParame {
    /**
     * 名字
     */
    private String productName;

    private Integer cateId;//分类
    private MultipartFile[] file;//图片
    private String productDetail;//详情
    private Integer productStock;//库存
    private BigDecimal productPrice;//价格
    private String productBrand;//品牌
}
